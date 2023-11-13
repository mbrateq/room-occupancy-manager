package com.mbrateq.manager;

import com.mbrateq.manager.dto.OccupancyRequest;
import com.mbrateq.manager.dto.OccupancyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/occupancy")
public class OccupancyController {

  private final OccupancyService occupancyService;

  @PostMapping("")
  public ResponseEntity<OccupancyResponse> createOccupancyReport(
      @Valid @RequestBody OccupancyRequest request) {
    return ResponseEntity.ok(
        occupancyService.optimizeOccupancy(
            request.getFreePremium(), request.getFreeEconomy(), request.getPrices()));
  }
}
