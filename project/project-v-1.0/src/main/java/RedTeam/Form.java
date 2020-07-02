package;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        if (isValid(submitterEmail))
        {
            this.submitterEmail = submitterEmail;
        }
        else System.err.println("You submitted an email that was invalid")
        this.submitterName = submitterName;
        this.submitterYUID = submitterYUID;

        // instantiate form meta data
        this.formID = UUID.randomUUID();
        this.timeSubmitted = new Date();
        this.status = GlobalFormStatus.SUBMITTED;

    }

    @Override
    public String toString() {
        String res = "";
        // TO-DO
        return res;
    }

    @Override
    public int hashCode() {
        // TO-DO
        int res = submitterEmail.hashCode() + submitterName.hashCode();
        return res;
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
