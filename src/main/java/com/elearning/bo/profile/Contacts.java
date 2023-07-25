package com.epmrdpt.bo.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class Contacts {

  @JsonProperty("Email")
  private String email;

  @JsonProperty("ContactPhone")
  private String contactPhone;

  @JsonProperty("Skype")
  private String skype;

  @JsonProperty("Allow")
  private boolean allowShowingContacts;

  public Contacts withEmail(String email) {
    this.email = email;
    return this;
  }

  public Contacts withContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
    return this;
  }

  public Contacts withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public Contacts withAllowShowingContacts(boolean allowShowingContacts) {
    this.allowShowingContacts = allowShowingContacts;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public String getSkype() {
    return skype;
  }

  public boolean getAllowShowingContacts() {
    return allowShowingContacts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Contacts)) {
      return false;
    }
    Contacts contacts = (Contacts) o;
    return allowShowingContacts == contacts.allowShowingContacts && email.equals(contacts.email) && contactPhone
        .equals(contacts.contactPhone) && skype.equals(contacts.skype);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, contactPhone, skype, allowShowingContacts);
  }

  @Override
  public String toString() {
    return "Contacts{" +
        "email='" + email + '\'' +
        ", contactPhone='" + contactPhone + '\'' +
        ", skype='" + skype + '\'' +
        ", allow=" + allowShowingContacts +
        '}';
  }
}
