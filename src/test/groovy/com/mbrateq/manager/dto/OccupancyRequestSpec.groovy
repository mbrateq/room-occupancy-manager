package com.mbrateq.manager.dto

import jakarta.validation.Validation
import jakarta.validation.Validator
import spock.lang.Specification

class OccupancyRequestSpec extends Specification {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    def "should return no errors if object is correct"() {
        given:
        final var request = new OccupancyRequest(givenFreePremium, givenFreeEconomy, prices)
        when:
        final var response = validator.validate(request)

        then:
        response.size() == 0

        where:
        givenFreePremium | givenFreeEconomy | prices
        3                | 3                | List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)
        7                | 5                | List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)
        2                | 7                | List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)
    }

    def "should throw exception when object didnt pass the validation"() {
        given:
        final var request = new OccupancyRequest(givenFreePremium, givenFreeEconomy, prices)
        when:
        final var response = validator.validate(request)

        then:
        response.size() == 1
        response.stream().findFirst().get().getMessage() == message

        where:
        givenFreePremium | givenFreeEconomy | prices                                                              | message
        3                | 3                | List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, -101D, 115D, 209D) | "must be greater than 0"
        7                | -5               | List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)  | "The value must be positive"
        -2               | 7                | List.of(23D, 45D, 155D, 374D, 22D, 99.99D, 100D, 101D, 115D, 209D)  | "The value must be positive"
    }
}
