package com.maatic;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class Address {
    @NotNull
    private String street;
    @NotNull
    private String state;
    private int zipCode;
    @NotNull
    private List<String> phoneNo;
    @NotNull
    private List<Country> country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<String> getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(List<String> phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode &&
                Objects.equals(street, address.street) &&
                Objects.equals(state, address.state) &&
                Objects.equals(phoneNo, address.phoneNo) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, state, zipCode, phoneNo, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", phoneNo=" + phoneNo +
                ", country=" + country +
                '}';
    }
}
