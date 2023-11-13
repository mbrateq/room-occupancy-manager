package com.mbrateq.manager

import com.mbrateq.manager.config.OccupancyServiceProperties
import com.mbrateq.manager.dto.OccupancyResponse
import spock.lang.Specification

class SimpleOccupancyServiceSpec extends Specification {

    private final OccupancyService occupancyResponseService = new SimpleOccupancyService(new OccupancyServiceProperties())
    List<Double> inputArray = List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)

    def "should return correct occupancy response"() {
        when:
        final var response = occupancyResponseService.optimizeOccupancy(givenFreePremium, givenFreeEconomy, inputArray)

        then:
        response.getUsagePremium() == expectedResponse.getUsagePremium()
        response.getUsageEconomy() == expectedResponse.getUsageEconomy()
        response.getUsagePremiumAmount() == expectedResponse.getUsagePremiumAmount()
        response.getUsageEconomyAmount() == expectedResponse.getUsageEconomyAmount()

        where:
        givenFreePremium | givenFreeEconomy | expectedResponse                                                                  | name
        3                | 3                | new OccupancyResponse(BigDecimal.valueOf(738), 3, BigDecimal.valueOf(167.99), 3)  | "TEST 1"
        7                | 5                | new OccupancyResponse(BigDecimal.valueOf(1054), 6, BigDecimal.valueOf(189.99), 4) | "TEST 2"
        2                | 7                | new OccupancyResponse(BigDecimal.valueOf(583), 2, BigDecimal.valueOf(189.99), 4)  | "TEST 3"
//  *** It's not possible to acheive the sum of 45.99 EUR out of the available values, regardless of the implementation - thats why the case is removed and set to a reasonable value
//  *** 7                | 1                | new OccupancyResponse(BigDecimal.valueOf(1153), 7, BigDecimal.valueOf(45.99), 1)| "TEST 4"
        7                | 1                | new OccupancyResponse(BigDecimal.valueOf(1153.99), 7, BigDecimal.valueOf(45), 1)  | "TEST 4 FIXED"
    }
}
