package com.savand.rms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.savand.rms.model.MicroService;
import com.savand.rms.model.RequiredMachineResource;
import com.softbistro.rms.machine_resource.CpuResource;
import com.softbistro.rms.machine_resource.MemoryResource;
import com.softbistro.rms.machine_resource.NetworkBandwidthResource;
import com.softbistro.rms.statcollector.IStatsCollector;
import com.softbistro.statCollector.CpuStatCollector;
import com.softbistro.statCollector.MemoryStatCollector;
import com.softbistro.statCollector.NetworkStatCollector;

/**
 * Instance is used for  analyzing service with machine technical compliance
 *
 * @author asavka
 *
 */
public class ServiceAlocateUtilImpl implements IServiceAllocateUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAlocateUtilImpl.class);

  @Override
  public boolean requirementsSuites(MicroService service) {
    LOGGER.debug("Fetching machine resources info for compliance analyze...");
    IStatsCollector memoryStatCollector = new MemoryStatCollector();
    IStatsCollector networkStatCollector = new NetworkStatCollector();
    IStatsCollector cpuStatCollector = new CpuStatCollector();

    CpuResource cpu = cpuStatCollector.getResourceInfo();
    RequiredMachineResource resourcesRequire = service.getResourcesRequire();

    if(cpu.getAvgPercIdl() < resourcesRequire.getCpu()){
      LOGGER.debug("CPU is busy.");
      return false;
    }

    MemoryResource memory = memoryStatCollector.getResourceInfo();

    if(memory.getFreeMB() < resourcesRequire.getMemory()){
      LOGGER.debug("Lack of memory");
      return false;
    }

    NetworkBandwidthResource network = networkStatCollector.getResourceInfo();

    if(network.getMBps() * 1000 < resourcesRequire.getNetworkBandwidth()){
      LOGGER.debug("Network speed is not high enough.");
      return false;
    }

    return true;
  }



}
