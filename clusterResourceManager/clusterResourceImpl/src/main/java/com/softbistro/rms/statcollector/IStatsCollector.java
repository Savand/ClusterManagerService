package com.softbistro.rms.statcollector;

import com.savand.rms.machine_resource.AbstractResource;

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
