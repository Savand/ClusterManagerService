package com.savand.rms.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.savand.rms.controller.CollectorControllerImpl;
import com.savand.rms.controller.ICollectorController;
import com.savand.rms.dao.MicroServiceDaoImpl;
import com.savand.rms.machine_resource.AbstractResource;
import com.savand.rms.model.MicroService;
import com.savand.rms.model.RequiredMachineResource;
import com.savand.rms.util.ServiceAlocateUtilImpl;
import com.softbistro.rms.statcollector.IStatsCollector;
import com.softbistro.statCollector.CpuStatCollector;
import com.softbistro.statCollector.MemoryStatCollector;
import com.softbistro.statCollector.NetworkStatCollector;


public class ClusterResourceManagerImpl implements IClusterResourceManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClusterResourceManagerImpl.class);


  @Override
  public <T extends AbstractResource> T getResourceInfo(IStatsCollector statsCollector) {
    LOGGER.debug("Fetching resource info");
    return collectorController.getResourceInfo(statsCollector);
  }

  @Override
  public List<MicroService> getRunningServices() {
    LOGGER.debug("Fetching currently running services info");
    return collectorController.getRunningServices();
  }

  @Override
  public boolean isServiceRunningOnLocal(MicroService service) {
    LOGGER.debug("Check if service '" + service.getName() + "' is running");
    collectorController.isServiceRunningOnLocal(service);
    return false;
  }

  @Override
  public boolean allocateService(MicroService service) {
    LOGGER.debug("Allocating '" + service.getName() + "' on local machine");
    return collectorController.allocateService(service);
  }

  @Override
  public boolean deallocateService(MicroService service) {
    LOGGER.debug("Deallocating '" + service.getName() + "' from local machine");
    return collectorController.deallocateService(service);
  }

  public static void main(String[] args) {
    IClusterResourceManager crm = new ClusterResourceManagerImpl(new CollectorControllerImpl(new MicroServiceDaoImpl(), new ServiceAlocateUtilImpl()));

    LOGGER.debug("Fetching memory info...");
    LOGGER.debug(String.valueOf(crm.getResourceInfo(new MemoryStatCollector())));
    LOGGER.debug("Fetching cpu info...");
    LOGGER.debug(String.valueOf(crm.getResourceInfo(new CpuStatCollector())));
    LOGGER.debug("Fetching network info...");
    LOGGER.debug(String.valueOf(crm.getResourceInfo(new NetworkStatCollector())));
    LOGGER.debug("Fetching currently running services...");
    LOGGER.debug(String.valueOf(crm.getRunningServices()));
    LOGGER.debug("Allocating service... expected success");
    MicroService microService = new MicroService();
    microService.setName("ms3");
    microService.setResourcesRequire(new RequiredMachineResource(100, 0.1,5000));
    crm.allocateService(microService);
    LOGGER.debug("Fetching currently running services...");
    LOGGER.debug(String.valueOf(crm.getRunningServices()));
    LOGGER.debug("Allocating service... expected fail");
    MicroService microService1 = new MicroService();
    microService1.setName("ms4");
    microService1.setResourcesRequire(new RequiredMachineResource(100, 0.9,110000));
    crm.allocateService(microService1);
    LOGGER.debug("Fetching currently running services...");
    LOGGER.debug(String.valueOf(crm.getRunningServices()));
    LOGGER.debug("Deallocating service... expected success");
    crm.deallocateService(microService);
    LOGGER.debug("Fetching currently running services...");
    LOGGER.debug(String.valueOf(crm.getRunningServices()));
  }


  //-------------- dependency injection part -----------------//

  public ClusterResourceManagerImpl(ICollectorController collectorController) {
    this.collectorController = collectorController;
  }

  private ICollectorController collectorController;


}
