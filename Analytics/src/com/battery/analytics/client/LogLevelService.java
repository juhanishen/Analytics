package com.battery.analytics.client;

import com.battery.analytics.shared.EPARecord;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loglevel")
public interface LogLevelService extends RemoteService {
	EPARecord[] getEAPRecords(String level) throws IllegalArgumentException;
}
