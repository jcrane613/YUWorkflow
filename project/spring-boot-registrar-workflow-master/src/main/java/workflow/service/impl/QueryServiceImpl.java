package workflow.service.impl;

import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.repository.ChangeTSRepository;
import workflow.repository.LeaveOfAbRepository;
import workflow.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.model.Form;
import workflow.repository.FormRepository;

@Service
public class QueryServiceImpl implements QueryService {

	private final FormRepository formRepository;
	private final ChangeTSRepository changeTSRepository;
	private final LeaveOfAbRepository leaveOfAbRepository;

	@Autowired
	public QueryServiceImpl(FormRepository formRepository, ChangeTSRepository changeTSRepository, LeaveOfAbRepository leaveOfAbRepository) {
		this.formRepository = formRepository;
		this.changeTSRepository = changeTSRepository;
		this.leaveOfAbRepository = leaveOfAbRepository;
	}

	@Override
	public Form getFormsByName(String name) {
		return formRepository.findByName(name).orElse(null);
	}

	@Override
	public ChangeTS getChangeTSFormByName(String name) {
		return changeTSRepository.findByName(name).orElse(null);
	}

	@Override
	public LeaveOfAb getLeaveOfAbFormByName(String name) {
		return leaveOfAbRepository.findByName(name).orElse(null);
	}

	@Override
	public Form getFormsBylastName(String lastName) {
		return formRepository.findByName(lastName).orElse(null);
	}

	@Override
	public ChangeTS getChangeTSFormBylastName(String lastName) {
		return changeTSRepository.findByName(lastName).orElse(null);
	}

	@Override
	public LeaveOfAb getLeaveOfAbFormBylastName(String lastName) {
		return leaveOfAbRepository.findByName(lastName).orElse(null);
	}

}
