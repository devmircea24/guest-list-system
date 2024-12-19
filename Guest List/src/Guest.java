import java.util.Objects;

public class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFullName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName(), getEmail(), getPhoneNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest guest)) return false;

        boolean result = Objects.equals(getFullName(), guest.getFullName())
                || Objects.equals(getEmail(), guest.getEmail())
                || Objects.equals(getPhoneNumber(), guest.getPhoneNumber());

        return Objects.equals(getFullName(), guest.getFullName())
                || Objects.equals(getEmail(), guest.getEmail())
                || Objects.equals(getPhoneNumber(), guest.getPhoneNumber());

    }

    @Override
    public String toString() {
        return "Nume: " + getLastName() + " " + getFirstName() + ", Email: " + getEmail() +
                ", Telefon: " + getPhoneNumber();
    }

    public String fullName() {
        return firstName + lastName;
    }
}
