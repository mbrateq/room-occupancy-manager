package com.mbrateq.manager;

import com.mbrateq.manager.config.OccupancyServiceProperties;
import com.mbrateq.manager.dto.OccupancyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleOccupancyService implements OccupancyService {

  private final OccupancyServiceProperties occupancyServiceProperties;

  @Override
  public OccupancyResponse optimizeOccupancy(
      int freePremium, int freeEconomy, List<Double> prices) {

    int economyUsage = 0;
    int premiumUsage = 0;
    double economyIncome = 0;
    double premiumIncome = 0;

    List<Double> bookingRequests = prices.stream().sorted(Comparator.reverseOrder()).toList();

    long premiumRequestsNumber =
        bookingRequests.stream()
            .filter(threshold -> threshold >= occupancyServiceProperties.getUpgradeThreshold())
            .count();
    long economyRequestsNumber = bookingRequests.size() - premiumRequestsNumber;

    boolean economyToPremiumPromotion =
        (occupancyServiceProperties.getPromotionActive()
            && (freePremium > premiumRequestsNumber && freeEconomy < economyRequestsNumber));

    for (Double amount : bookingRequests) {
      if (amount >= 100D) {
        if (freePremium > 0) {
          freePremium--;
          premiumUsage++;
          premiumIncome += amount;
        }
      } else {
        if (economyToPremiumPromotion) {
          freePremium--;
          premiumUsage++;
          premiumIncome = premiumIncome + amount;
          economyToPremiumPromotion = false;
        } else {
          if (freeEconomy > 0) {
            freeEconomy--;
            economyUsage++;
            economyIncome += amount;
          }
        }
      }
    }

    return new OccupancyResponse(
        BigDecimal.valueOf(premiumIncome),
        premiumUsage,
        BigDecimal.valueOf(economyIncome),
        economyUsage);
  }
}
