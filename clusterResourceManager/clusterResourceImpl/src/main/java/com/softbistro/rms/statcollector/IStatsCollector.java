package com.softbistro.rms.statcollector;

import com.softbistro.rms.machine_resource.AbstractResource;

/**
 *  Interface should be implemented by each statistic collector</br>
 *  instance
 *
 *  @author Savand
 *
 */

public interface IStatsCollector {
  /**
   *
   * @return specific resource information
   */
  public <T extends AbstractResource> T getResourceInfo();
}
