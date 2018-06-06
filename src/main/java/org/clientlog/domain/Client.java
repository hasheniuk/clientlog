package org.clientlog.domain;

import org.clientlog.dto.ClientDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
public class Client {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Client() {}

    public Client(ClientDTO clientDTO) {
        this.id = clientDTO.getId();
        this.firstName = clientDTO.getFirstName();
        this.middleName = clientDTO.getMiddleName();
        this.lastName = clientDTO.getLastName();
        this.email = clientDTO.getEmail();
        this.phone = clientDTO.getPhone();
    }

    @PrePersist
    private void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.updated = now;
    }

    @PreUpdate
    private void preUpdate() {
        this.updated = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", unique = true, nullable = false)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "updated")
    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
