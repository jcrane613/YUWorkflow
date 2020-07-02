package RedTeam;

import java.util.List;

public class LeaveOfAbsenceForm extends Form {
    
    public enum UndergradSchool{
        KATZ, SCW, SSSB, YC;
    }
    public enum GradSchool{
        AGS , BRG ,CSL ,FGS ,KATZ , RIETS , SCW , SSSB , WSSW;
    }

    String semesterOfLeave;
    List<UndergradSchool> undergradSchoolsOfLeave;
    List<GradSchool> gradSchoolsOfLeave;
    String reasonForLeave;
    String lastDateOfAttendence;


    public LeaveOfAbsenceForm(String submitterEmail, String submitterName, int submitterYUID,
    String semesterOfLeave, List<UndergradSchool> undergradSchoolsOfLeave, List<GradSchool> gradSchoolsOfLeave, String reasonForLeave, String lastDateOfAttendece){
        super(submitterEmail, submitterName, submitterYUID);

        this.semesterOfLeave = semesterOfLeave;
        this.undergradSchoolsOfLeave = undergradSchoolsOfLeave;
        this.gradSchoolsOfLeave = gradSchoolsOfLeave;
        this.reasonForLeave = reasonForLeave;
        this.lastDateOfAttendence = lastDateOfAttendece;

    }
}
