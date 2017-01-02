package com.softbistro.rms.controller;

import java.util.List;

import com.savand.rms.model.MicroService;
import com.softbistro.rms.machine_resource.AbstractResource;
import com.softbistro.rms.statcollector.IStatsCollector;

public interface ICollectorController {
  public <T extends AbstractResource> T getResourceInfo(IStatsCollector statsCollector);

  public List<MicroService> getRunningServices();

  public boolean isServiceRunningOnLocal(MicroService service);

  public boolean allocateService(MicroService service);

  public boolean deallocateService(MicroService service);
  
}
