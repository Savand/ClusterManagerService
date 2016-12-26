package com.savand.rms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.savand.rms.dao.IMicroServiceDao;
import com.savand.rms.machine_resource.AbstractResource;
import com.savand.rms.model.MicroService;
import com.savand.rms.util.IServiceAllocateUtil;
import com.softbistro.rms.statcollector.IStatsCollector;

public class CollectorControllerImpl implements ICollectorController{

  private static final Logger LOGGER = LoggerFactory.getLogger(CollectorControllerImpl.class);

  @Override
  public <T extends AbstractResource> T getResourceInfo(IStatsCollector statsCollector) {

    T result = null;
    Class<? extends IStatsCollector> clazz = statsCollector.getClass();
    IStatsCollector statsCollectorInstance = null;

    try {
      LOGGER.debug("Start fetching " + clazz.getSimpleName() + " info");
      statsCollectorInstance = clazz.newInstance();
      LOGGER.debug("Instantiating " + clazz.getSimpleName());
    } catch (InstantiationException e) {
      LOGGER.error("Error while " + clazz.getSimpleName() + " processing");
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      LOGGER.error("Error while " + clazz.getSimpleName() + " processing");
      e.printStackTrace();
    }

    result = statsCollectorInstance.getResourceInfo();
    LOGGER.debug("Fetched " + clazz.getSimpleName() + " info");

    return result;
  }

  @Override
  public List<MicroService> getRunningServices() {
    LOGGER.debug("Fetching running services");
    return mServiceDao.getRunningServices();
  }

  @Override
  public boolean isServiceRunningOnLocal(MicroService service) {
    LOGGER.debug("Checking if " + service.getName() + " sevice is running on local machine");
    return mServiceDao.isServiceRunningOnLocal(service);
  }

  @Override
  public boolean allocateService(MicroService service) {
    boolean result = false;
    boolean isCompliantServiceMachineRequirement = serviceAllocateUtil.requirementsSuites(service);

    if(isCompliantServiceMachineRequirement){
      result = mServiceDao.allocateService(service);
      LOGGER.debug(service.getName() + " sevice successfuly allocated");
      result = true;
    } else {
      LOGGER.debug(service.getName() + " sevice WAS NOT allocated");
    }

    return result;
  }

  @Override
  public boolean deallocateService(MicroService service) {
    boolean deallocateService = mServiceDao.deallocateService(service);
    LOGGER.debug(service.getName() + " sevice successfuly deallocated");

    return deallocateService;
  }


  //-------------- dependency injection part -----------------//

  private IMicroServiceDao mServiceDao;
  private IServiceAllocateUtil serviceAllocateUtil;


  public CollectorControllerImpl(IMicroServiceDao mServiceDao, IServiceAllocateUtil serviceAllocateUtil) {
    this.mServiceDao = mServiceDao;
    this.serviceAllocateUtil = serviceAllocateUtil;
  }


}
