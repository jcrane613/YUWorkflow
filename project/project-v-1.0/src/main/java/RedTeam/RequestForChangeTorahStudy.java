package main.java.RedTeam;
import java.lang.reflect.Type;
import java.util.List;
import main.java.Enums.Types;

public class RequestForChangeTorahStudy extends Form {

    public enum CurrentProgram{
        IBC, JSS, MYP, SBMP;
    }
    public enum SchoolAttending{
        SSSB, YC;
    }
    public enum CurrentClass{
        Freshman, Sophomore, Junior, Senior;
    }

    private String mailAddress;
    private String city;
    private String state;
    private int zipcode;
    private int phonenumber;
    private Types.TorahStudiesProgram currentProgram;
    private Types.TorahStudiesProgram switchToProgram;
    private Types.School schoolAttending;
    private Types.ClassStanding currentClass;

    public RequestForChangeTorahStudy(String submitterName, String submitterEmail, int submitterYUID, String mailAddress, String city, String state, int zipCode, int phoneNumber,
                              Types.TorahStudiesProgram currentProgram,  Types.TorahStudiesProgram switchToProgram, Types.School schoolAttending, Types.ClassStanding currentClass)
    {
        super(submitterName, submitterEmail, submitterYUID);
        this.mailAddress = mailAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipCode;
        this.phonenumber = phoneNumber;
        this.currentProgram = currentProgram;
        this.switchToProgram = switchToProgram;
        this.schoolAttending = schoolAttending;
        this.currentClass = currentClass;
    }

    //An Attempt to model the workflow
    

}

