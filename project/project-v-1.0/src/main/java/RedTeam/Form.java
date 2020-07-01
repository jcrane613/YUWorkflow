import java.util.Date;
import java.util.UUID;

import java.util.UUID;
import java.util.Date;

public abstract class Form {
    
    enum GlobalFormStatus {
        SUBMITTED, IN_PROGRESS, APPROVED, DENIED;
    }
    
    // Form meta-data
    Date timeSubmitted;
    final UUID formID;
    GlobalFormStatus status;
    // LocalFormStatus

    //Form data
    String submitterEmail;
    String submitterName;
    int submitterYUID;

    public Form(String submitterEmail, String submitterName, int submitterYUID) {
        // instantiate form data
        this.submitterEmail = submitterEmail;
        this.submitterName = submitterName;
        this.submitterYUID = submitterYUID;
        
        // instantiate form meta data
        this.formID = UUID.randomUUID();
        this.timeSubmitted = new Date();
        this.status = GlobalFormStatus.SUBMITTED;

    }

    @Override 
    public String toString() {
        String res "";
        // TO-DO
        return res;
    }

    @Override
    public int hashCode() {
        // TO-DO
        int res = submitterEmail.hashCode() + submitterName.hashCode();
        return res;
    }

}