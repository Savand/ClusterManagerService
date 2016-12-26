package com.savand.rms.model;

public class MicroService {

  private Integer id;
  private String name;
  private RequiredMachineResource resourcesRequire;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   *
   * @return resources required by SLA for this service
   */
  public RequiredMachineResource getResourcesRequire() {
    return resourcesRequire;
  }

  public void setResourcesRequire(RequiredMachineResource resourcesRequire) {
    this.resourcesRequire = resourcesRequire;
  }

  @Override
  public String toString() {
    return "MicroService [name=" + name + ", resourcesRequire=" + resourcesRequire + "]";
  }



}
