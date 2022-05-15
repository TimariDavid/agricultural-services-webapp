package hu.nye.progkor.webapp.model;

import java.util.Objects;

public class Agriculture {

  private Long id;
  private String firstName;
  private String lastName;
  private ServicesEnum servicesEnum;
  private Country country;
  private String email;

  public Agriculture() {
  }

  public Agriculture(Long id, String firstName, String lastName,
                     ServicesEnum servicesEnum, Country country, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.servicesEnum = servicesEnum;
    this.country = country;
    this.email = email;
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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public ServicesEnum getServicesEnum() {
    return servicesEnum;
  }

  public void setServicesEnum(ServicesEnum servicesEnum) {
    this.servicesEnum = servicesEnum;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Agriculture that = (Agriculture) o;
    return email == that.email && Objects.equals(id, that.id)
            && Objects.equals(firstName, that.firstName)
            && Objects.equals(lastName, that.lastName)
            && servicesEnum == that.servicesEnum && country == that.country;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, servicesEnum, country, email);
  }

  @Override
  public String toString() {
    return "Agriculture{"
            + "id=" + id
            + ", firstName='" + firstName + '\''
            + ", lastName='" + lastName + '\''
            + ", servicesEnum=" + servicesEnum
            + ", country=" + country
            + ", email=" + email
            + '}';
  }
}
