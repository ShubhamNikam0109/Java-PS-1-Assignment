import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leadId;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name should only contain alphabets")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]*$", message = "Middle Name should only contain alphabets")
    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name should only contain alphabets")
    private String lastName;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile Number should start with a digit greater than 5 and contain 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "^(Male|Female|Others)$", message = "Gender should be one of Male, Female, or Others")
    private String gender;

    @NotNull(message = "Date of birth is mandatory")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be a valid email address")
    private String email;


    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
