package org.clientlog.dto;

public class ClientRequest extends PageRequestDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean anyMatch;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getAnyMatch() {
        return anyMatch;
    }

    public void setAnyMatch(Boolean anyMatch) {
        this.anyMatch = anyMatch;
    }
}
