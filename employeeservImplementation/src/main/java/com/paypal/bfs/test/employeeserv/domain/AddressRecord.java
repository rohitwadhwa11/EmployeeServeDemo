package com.paypal.bfs.test.employeeserv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class AddressRecord {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private long addressId;

    @NotNull
    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "zipCode")
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private EmployeeRecord employeeRecord;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public EmployeeRecord getEmployeeRecord() {
        return employeeRecord;
    }

    public void setEmployeeRecord(EmployeeRecord employeeRecord) {
        this.employeeRecord = employeeRecord;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
