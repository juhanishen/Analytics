package com.battery.analytics.client;

import com.battery.analytics.shared.EPARecord;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LogLevelServiceAsync {
	void getEAPRecords(String level, AsyncCallback<EPARecord[]> callback) throws IllegalArgumentException;
}
