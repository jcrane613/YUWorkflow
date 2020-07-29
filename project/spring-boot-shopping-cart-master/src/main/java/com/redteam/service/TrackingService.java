package com.redteam.service;

import com.redteam.model.ChangeTS;
import com.redteam.model.Form;
import com.redteam.model.LeaveOfAb;

public interface TrackingService {

	Form getFormByTrackingId(String trackingId);

	ChangeTS getChangeTSFormByTrackingId(String trackingId);

	LeaveOfAb getLeaveOfAbFormByTrackingId(String trackingId);

}
