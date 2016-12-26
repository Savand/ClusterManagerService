package com.savand.rms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.savand.rms.model.MicroService;
import com.savand.rms.model.RequiredMachineResource;

public class MicroServiceDaoImpl implements IMicroServiceDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceDaoImpl.class);

  private AtomicInteger currentId = new AtomicInteger(1);
  Map<Integer, MicroService> services = new ConcurrentHashMap<>();


  public MicroServiceDaoImpl() {
    LOGGER.debug("Start creating microServiceDaoImpl...");
    init();
    LOGGER.debug("Finished creating microServiceDaoImpl");
  }

  private void init() {
    LOGGER.debug("Start filling fake repository with initial data...");
    MicroService ms1 = new MicroService();
    ms1.setId(currentId.getAndIncrement());
    ms1.setName("MicroService1");
    ms1.setResourcesRequire(new RequiredMachineResource(35, 0.1,1000));

    services.put(ms1.getId(), ms1);

    MicroService ms2 = new MicroService();
    ms2.setId(currentId.getAndIncrement());
    ms2.setName("MicroService1");
    ms2.setResourcesRequire(new RequiredMachineResource(100, 0.1,5000));

    services.put(ms2.getId(), ms2);
    LOGGER.debug("Done with filling fake repository");

  }

  @Override
  public List<MicroService> getRunningServices() {
    LOGGER.debug("Fetching services from repository");
    return new ArrayList<MicroService>(services.values());
  }

  @Override
  public boolean isServiceRunningOnLocal(MicroService service) {
    LOGGER.debug("Check if spesified microservice is running on local machine");
    return services.containsKey(service.getId());
  }

  @Override
  public boolean allocateService(MicroService service) {
    LOGGER.debug("Allocating microservice on local machine");
    service.setId(currentId.getAndIncrement());
    services.put(service.getId(), service);
    LOGGER.debug("Service successfully started, resources allocated");
    return true;
  }

  @Override
  public boolean deallocateService(MicroService service) {
    LOGGER.debug("Stopping microservice");
    services.remove(service.getId());
    LOGGER.debug("Microservice removed, resources deallocated");
    return true;
  }

}
