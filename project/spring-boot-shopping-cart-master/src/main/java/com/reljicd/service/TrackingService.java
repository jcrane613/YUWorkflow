package com.reljicd.service;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;

public interface TrackingService {

	Form getFormByTrackingId(String trackingId);

	ChangeTS getChangeTSFormByTrackingId(String trackingId);

	LeaveOfAb getLeaveOfAbFormByTrackingId(String trackingId);

}
