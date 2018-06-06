package org.clientlog.dto;

import org.clientlog.domain.Client;

public class ClientDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;

    public ClientDTO() {}

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.middleName = entity.getMiddleName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
