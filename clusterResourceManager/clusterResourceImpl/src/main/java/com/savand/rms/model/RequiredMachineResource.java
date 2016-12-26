package com.savand.rms.model;


/**
 * Carries the technical requirements</br>
 * specified by SLA
 *
 *
 * @author Savand
 *
 */
public class RequiredMachineResource {

  private Integer id;

  /**
   * Memory size specified by SLA, MB
   */
  private Integer memory;

  /**
   * Cpu type specified by SLA
   */
  private double cpu;

  /**
   * Network bandwidth specified by SLA, KBps
   */
  private Integer networkBandwidth;

  public RequiredMachineResource() {}

  /**
   *
   * @param memory - required memory size specified by SLA, MB
   * @param cpu - cpu idle part specified by SLA
   * @param networkBandwidth - networkBandwidth speed required specified by SLA, KBps
   */
  public RequiredMachineResource(Integer memory, double cpu, Integer networkBandwidth) {
    this.memory = memory;
    this.cpu = cpu;
    this.networkBandwidth = networkBandwidth;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   *
   * @return memory MB by SLA
   */
  public Integer getMemory() {
    return memory;
  }

  /**
  *
  * set cpu requirements by SLA
  * @param required memory, MB
  */
  public void setMemory(Integer memory) {
    this.memory = memory;
  }

  /**
   *
   * @return cpu requirements by SLA (cpu idle)
   */
  public double getCpu() {
    return cpu;
  }

  /**
  *
  * set cpu requirements by SLA
  * @param cpu idle
  */
  public void setCpu(double cpu) {
    this.cpu = cpu;
  }

  /**
   *
   * @return network requirements in SLA (speed MBps)
   */
  public Integer getNetworkBandwidth() {
    return networkBandwidth;
  }

  /**
  *
  * set network bandwidth requirements by SLA
  * @param network speed, MBps
  */
  public void setNetworkBandwidth(Integer networkBandwidth) {
    this.networkBandwidth = networkBandwidth;
  }

  @Override
  public String toString() {
    return "ResourcesRequire [memory=" + memory + ", cpu=" + cpu + ", networkBandwidth=" + networkBandwidth + "]";
  }


}
