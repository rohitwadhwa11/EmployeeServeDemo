package com.paypal.bfs.test.employeeserv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class EmployeeRecord {

    @Id
    @Column(name = "empid", nullable = false)
    private Integer empId;

    @NotNull
    @Column(name = "firstname")
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    private String lastName;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressRecord address;

    @NotNull
    @Column(name="dob")
    private String dateOfBirth;

    public Integer getId() {
        return empId;
    }

    public void setId(Integer id) {
        this.empId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public AddressRecord getAddress() {
        return address;
    }

    public void setAddress(AddressRecord address) {
        this.address = address;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
