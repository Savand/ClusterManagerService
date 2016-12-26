package com.savand.rms.machine_resource;

public class MemoryResource extends AbstractResource {


  private Long freeMB;

  private Long busyMB;

  public Long getFreeMB() {
    return freeMB;
  }

  public void setFreeMB(Long freeMB) {
    this.freeMB = freeMB;
  }

  public Long getBusyMB() {
    return busyMB;
  }

  public void setBusyMB(Long busyMB) {
    this.busyMB = busyMB;
  }

  @Override
  public String toString() {
    return "MemoryResource [freeMB=" + freeMB + ", busyMB=" + busyMB + "]";
  }

}
