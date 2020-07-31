package workflow.service.impl;

import workflow.model.ChangeTS;
import workflow.model.Form;
import workflow.model.LeaveOfAb;
import workflow.repository.ChangeTSRepository;
import workflow.repository.FormRepository;
import workflow.repository.LeaveOfAbRepository;
import workflow.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingServiceImpl implements TrackingService {

	private final FormRepository formRepository;
	private final ChangeTSRepository changeTSRepository;
	private final LeaveOfAbRepository leaveOfAbRepository;

    @Autowired
    public TrackingServiceImpl(FormRepository formRepository, ChangeTSRepository changeTSRepository, LeaveOfAbRepository leaveOfAbRepository) {
        this.formRepository = formRepository;
        this.changeTSRepository = changeTSRepository;
        this.leaveOfAbRepository = leaveOfAbRepository;
    }

	@Override
	public Form getFormByTrackingId(String trackingId) {
		Form form = formRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	@Override
	public ChangeTS getChangeTSFormByTrackingId(String trackingId) {
		ChangeTS form = changeTSRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	@Override
	public LeaveOfAb getLeaveOfAbFormByTrackingId(String trackingId) {
		LeaveOfAb form = leaveOfAbRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	
	
}
