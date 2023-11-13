package com.mbrateq.manager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccupancyRequest {

  @Min(value = 0L, message = "The value must be positive")
  private Integer freePremium;

  @Min(value = 0L, message = "The value must be positive")
  private Integer freeEconomy;

  private List<@Positive Double> prices;
}
