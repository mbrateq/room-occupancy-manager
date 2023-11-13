package com.mbrateq.manager;

import com.mbrateq.manager.dto.OccupancyResponse;

import java.util.List;

public interface OccupancyService {
  OccupancyResponse optimizeOccupancy(int freePremium, int freeEconomy, List<Double> prices);
}
