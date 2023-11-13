package com.mbrateq.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccupancyResponse {

  private BigDecimal usagePremiumAmount;
  private Integer usagePremium;

  private BigDecimal usageEconomyAmount;
  private Integer usageEconomy;
}
