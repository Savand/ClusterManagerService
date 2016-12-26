package com.savand.rms.machine_resource;

public class NetworkBandwidthResource extends AbstractResource {
  private long MBps;

  public long getMBps() {
    return MBps;
  }

  public void setMBps(long mBps) {
    MBps = mBps;
  }

  @Override
  public String toString() {
    return "NetworkBandwidthResource [MBps=" + MBps + "]";
  }


}
