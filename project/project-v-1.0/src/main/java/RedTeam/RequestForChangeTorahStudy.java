package main.java.RedTeam;
import java.util.List;

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
    private CurrentProgram currentProgram;
    private CurrentProgram switchToProgram;
    private SchoolAttending schoolAttending;
    private CurrentClass currentClass;

    public RequestForChangeTorahStudy(String submitterName, String submitterEmail, int submitterYUID, String mailAddress, String city, String state, int zipCode, int phoneNumber,
                              CurrentProgram currentProgram, CurrentProgram switchToProgram, SchoolAttending schoolAttending, CurrentClass currentClass)
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
}

