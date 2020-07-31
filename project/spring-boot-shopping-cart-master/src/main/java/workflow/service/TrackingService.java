package workflow.service;

import workflow.model.ChangeTS;
import workflow.model.Form;
import workflow.model.LeaveOfAb;

public interface TrackingService {

	Form getFormByTrackingId(String trackingId);

	ChangeTS getChangeTSFormByTrackingId(String trackingId);

	LeaveOfAb getLeaveOfAbFormByTrackingId(String trackingId);

}
