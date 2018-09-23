package br.com.crud.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.util.Objects.isNull;


@Entity
@Table(name = "ADDRESS")
public class Address {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "STREET", nullable = false)
    @NotBlank(message = "The street field is required")
    private String street;

    @Column(name = "NUMBER", nullable = false, length = 6)
    @NotNull(message = "The number field is required")
    private Integer number;

    @Column(name = "POSTAL_CODE", nullable = false, length = 12)
    @NotBlank(message = "The postal code field is required")
    private String postalCode;

    @Column(name = "CITY", nullable = false, length = 36)
    @NotBlank(message = "The city field is required")
    private String city;

    @Column(name = "STATE", nullable = false, length = 26)
    @NotBlank(message = "The state field is required")
    private String state;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "NEIGHBORHOOD", length = 36)
    private String neighborhood;


    public Address(String street, Integer number, String postalCode, String city, String state, String complement, String neighborhood) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.complement = complement;
        this.neighborhood = neighborhood;
    }

    protected Address() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + (isNull(street) ? "" : street) +'\'' +
                ", number=" + (isNull(number) ? "" : number.toString())  +
                ", postalCode='" + (isNull(postalCode) ? "" : postalCode) + '\'' +
                ", city='" + (isNull(city) ? "" : city) + '\'' +
                ", complement='" + (isNull(complement) ? "" : complement) + '\'' +
                ", neighborhood='" + (isNull(neighborhood) ? "" : neighborhood) + '\'' +
                '}';
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}