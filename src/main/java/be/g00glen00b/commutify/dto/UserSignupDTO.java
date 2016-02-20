package be.g00glen00b.commutify.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserSignupDTO {
    @NotNull(message = "userSignup.firstName.notNull")
    @Size(max = 32, message = "userSignup.firstName.size")
    private String firstName;
    @Size(max = 32, message = "userSignup.name.size")
    private String name;
    @NotNull(message = "userSignup.email.notNull")
    @Size(max = 128, message = "userSignup.email.size")
    @Email(message = "userSignup.email.email")
    private String email;
    @NotNull(message = "userSignup.password.notNull")
    private String password;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
