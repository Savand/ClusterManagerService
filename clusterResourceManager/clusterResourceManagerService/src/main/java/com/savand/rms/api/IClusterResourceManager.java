package com.savand.rms.api;

import java.util.List;

import com.savand.rms.machine_resource.AbstractResource;
import com.savand.rms.model.MicroService;
import com.softbistro.rms.statcollector.IStatsCollector;

/**
 * Service allows to track and manage running microservices, in particular:
 * <li>track machine technical stats (memory usage, cpu,network etc)</br>
 * <li>allocate resources for a micro-service
 * <li>deallocate resources for a micro-service instance running on a machine

 * @author Savand
 *
 */
public interface IClusterResourceManager {

  /**
   *
   * @param iplementation of IStatsCollector
   * @return specific resource info depending on implementation (memory, cpu, network bandwidth)
   */
  public <T extends AbstractResource> T getResourceInfo(IStatsCollector statsCollector);

  /**
   *
   * @return microservices allocated on local machine
   */
  public List<MicroService> getRunningServices();

  /**
   *
   * @param service
   * @return boolean
   */
  public boolean isServiceRunningOnLocal(MicroService service);

  /**
   * Allocates
   * @param service
   * @return is service successfully allocated
   */
  public boolean allocateService(MicroService service);

  /**
   *
   * @param service
   * @return is service successfully deallocated
   */
  public boolean deallocateService(MicroService service);

}
