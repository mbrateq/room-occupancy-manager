package com.mbrateq.manager

import com.fasterxml.jackson.databind.ObjectMapper
import com.mbrateq.manager.config.OccupancyServiceProperties
import com.mbrateq.manager.dto.OccupancyRequest
import com.mbrateq.manager.dto.OccupancyResponse
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OccupancyControllerSpec extends Specification {

    List<Double> inputArray = List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new OccupancyController(new SimpleOccupancyService(new OccupancyServiceProperties()))).build()

    ObjectMapper objectMapper = new ObjectMapper()

    def "should return correct occupancy response"() {
        given:
        final var OccupancyRequest request = new OccupancyRequest(givenFreePremium, givenFreeEconomy, inputArray)
        when:
        final var response = mockMvc.perform(
                post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))

        then:
        final var responseBody = objectMapper.readValue(response.andReturn().getResponse().getContentAsString(), OccupancyResponse.class)
        response.andExpect(status().isOk())

        responseBody.getUsagePremium() == expectedResponse.getUsagePremium()
        responseBody.getUsageEconomy() == expectedResponse.getUsageEconomy()
        responseBody.getUsagePremiumAmount() == expectedResponse.getUsagePremiumAmount()
        responseBody.getUsageEconomyAmount() == expectedResponse.getUsageEconomyAmount()

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
