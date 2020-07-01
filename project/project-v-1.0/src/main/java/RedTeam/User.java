package RedTeam;

import java.util.UUID;

public abstract class User {
    
    String firstName;
    String middleName;
    String lastName;
    String email;
    String password;

    public User(String fullName, String email, String password) {
        String[] names = fullName.split(" ");
        this.firstName = names[0];
        this.middleName = (names.length > 2) ? names[1] : "";
        this.lastName = names[names.length-1];
    }


}