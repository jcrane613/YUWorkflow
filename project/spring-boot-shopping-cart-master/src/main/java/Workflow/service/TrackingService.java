package Workflow.service;

import Workflow.model.ChangeTS;
import Workflow.model.Form;
import Workflow.model.LeaveOfAb;

public interface TrackingService {

	Form getFormByTrackingId(String trackingId);

	ChangeTS getChangeTSFormByTrackingId(String trackingId);

	LeaveOfAb getLeaveOfAbFormByTrackingId(String trackingId);

}
