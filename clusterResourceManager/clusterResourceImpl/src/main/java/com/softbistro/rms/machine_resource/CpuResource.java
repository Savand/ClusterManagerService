package com.softbistro.rms.machine_resource;

public class CpuResource extends AbstractResource {
  private int totalCores;
  private int Mhz;
  private double avgPercIdl;
  
  public int getTotalCores() {
    return totalCores;
  }
  
  public void setTotalCores(int totalCores) {
    this.totalCores = totalCores;
  }
  
  public int getMhz() {
    return Mhz;
  }
  
  public void setMhz(int mhz) {
    Mhz = mhz;
  }
  
  public double getAvgPercIdl() {
    return avgPercIdl;
  }
  
  public void setAvgPercIdl(double avgPercIdl) {
    this.avgPercIdl = avgPercIdl;
  }

  @Override
  public String toString() {
    return "CpuResource [totalCores=" + totalCores + ", Mhz=" + Mhz + ", avgPercIdl=" + avgPercIdl + "]";
  }
  
  
  
  
}
