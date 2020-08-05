package workflow.service.impl;

import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.service.ProcessingPageService;
import workflow.util.CurrentState;
import workflow.model.Form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ProcessingPageServiceImpl implements ProcessingPageService {

    private Form form = null;
    private LeaveOfAb leaveOfAb = null;
    private ChangeTS changeTS = null;
    
    @Override
    public void addForm(Form form) {
    	this.form = form;
    	this.changeTS = null;
    	this.leaveOfAb = null;
    }
    
    @Override
	public void addChangeTSForm(ChangeTS changeTS) {
    	this.changeTS = changeTS; 
    	this.form = null;
    	this.leaveOfAb = null;
	}

	@Override
	public void addLeaveOfAbForm(LeaveOfAb leaveOfAb) {
		this.leaveOfAb = leaveOfAb;
    	this.form = null;
    	this.changeTS = null;
	}
    
    @Override
    public void approveForm(Form form) {
    	form.setCurrent(form.getCurrent() + 1);
    	if (form.getCurrent() > form.getTotalSteps()) form.setStatus("APPROVED");
    	this.form = null;
    }
    
    @Override
	public void approveChangeTSForm(ChangeTS form) {
    	form.setCurrent(form.getCurrent() + 1);
    	if (form.getCurrent() > form.getTotalSteps()) form.setStatus("APPROVED");
		this.changeTS = null;
	}

	@Override
	public void approveLeaveOfAbForm(LeaveOfAb form) {
		form.setCurrent(form.getCurrent() + 1);
    	if (form.getCurrent() > form.getTotalSteps()) form.setStatus("APPROVED");
		this.leaveOfAb = null;
	}
    
    @Override
    public void denyForm(Form form) {
    	form.setStatus("DENIED");
    	int denialStep = form.getCurrent();
    	form.setCurrent(new Integer(-1*denialStep));
    	this.form = null;
    }
    
    @Override
    public void denyChangeTSForm(ChangeTS form) {
    	form.setStatus("DENIED");
    	int denialStep = form.getCurrent();
    	form.setCurrent(new Integer(-1*denialStep));
    	this.changeTS = null;
    }
    
    @Override
    public void denyLeaveOfAbForm(LeaveOfAb form) {
    	form.setStatus("DENIED");
    	int denialStep = form.getCurrent();
    	form.setCurrent(new Integer(-1*denialStep));
    	this.leaveOfAb = null;
    }
        
    @Override
	public Form getForm() {
		if (this.form != null && this.form.getCurrentApprover().equals(CurrentState.getCurrentUsername())) {
			return this.form;
		}
    	return null;
	}
    
    @Override
   	public ChangeTS getChangeTSForm() {
   		if (this.changeTS != null && this.changeTS.getCurrentApprover().equals(CurrentState.getCurrentUsername())) {
   			return this.changeTS;
   		}
       	return null;
   	}
    
    @Override
   	public LeaveOfAb getLeaveOfAbForm() {
   		if (this.leaveOfAb != null && this.leaveOfAb.getCurrentApprover().equals(CurrentState.getCurrentUsername())) {
   			return this.leaveOfAb;
   		}
       	return null;
   	}
    
}
