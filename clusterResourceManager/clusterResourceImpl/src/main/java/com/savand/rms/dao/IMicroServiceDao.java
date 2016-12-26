package com.savand.rms.dao;

import java.util.List;

import com.savand.rms.model.MicroService;

public interface IMicroServiceDao {

  List<MicroService> getRunningServices();

  boolean isServiceRunningOnLocal(MicroService service);

  boolean allocateService(MicroService service);

  boolean deallocateService(MicroService service);

}
