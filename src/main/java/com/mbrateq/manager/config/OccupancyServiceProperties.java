package com.mbrateq.manager.config;

import lombok.Data;

@Data
public class OccupancyServiceProperties {
  private static final Long DEFAULT_UPGRADE_THRESHOLD = 100L;
  private static final Boolean PROMOTION_ACTIVE = true;

  private Long upgradeThreshold = DEFAULT_UPGRADE_THRESHOLD;
  private Boolean promotionActive = PROMOTION_ACTIVE;
}
