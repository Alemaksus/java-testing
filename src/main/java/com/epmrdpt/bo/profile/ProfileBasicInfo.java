package com.epmrdpt.bo.profile;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.NUMBER;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ProfileBasicInfo {

  @JsonProperty("UserId")
  private int userId;

  @JsonProperty("FirstName")
  private String firstName;

  @JsonProperty("LastName")
  private String lastName;

  @JsonProperty("NativeName")
  private String nativeName;

  @JsonProperty("Email")
  private String email;

  @JsonProperty("ContactPhone")
  private String contactPhone;

  @JsonProperty("BirthDate")
  @JsonFormat(shape = STRING)
  private LocalDate birthDate;

  @JsonProperty("CreatedAt")
  @JsonFormat(shape = STRING)
  private LocalDate createdAt;

  @JsonProperty("CityId")
  private int cityId;

  @JsonProperty("City")
  private String city;

  @JsonProperty("CountryId")
  private int countryId;

  @JsonProperty("Country")
  private String country;

  @JsonProperty("Region")
  private String region;

  @JsonProperty("SocialNetworkSkype")
  private String socialNetworkSkype;

  @JsonProperty("SocialNetworkVk")
  private String socialNetworkVk;

  @JsonProperty("SocialNetworkFacebook")
  private String socialNetworkFacebook;

  @JsonProperty("SocialNetworkTelegram")
  private String socialNetworkTelegram;

  @JsonProperty("SocialNetworkOdnoklassniki")
  private String socialNetworkOdnoklassniki;

  @JsonProperty("SocialNetworkLinkedIn")
  private String socialNetworkLinkedIn;

  @JsonProperty("SocialNetworkGitHub")
  private String socialNetworkGitHub;

  @JsonProperty("PreferredMethodOfCommunication")
  private PreferredMethodOfCommunication preferredMethodOfCommunication;

  @JsonProperty("IsCyrillicSymbolsAllowed")
  private boolean isCyrillicSymbolsAllowed;

  @JsonProperty("AdditionalInfo")
  private String additionalInfo;

  @JsonProperty("ProfessionalActivities")
  private String professionalActivities;

  @JsonProperty("PhotoId")
  private int photoId;

  @JsonProperty("Portfolio")
  private String portfolio;

  @JsonProperty("StaffingDeskId")
  private String staffingDeskId;

  public ProfileBasicInfo withId(int id) {
    this.userId = id;
    return this;
  }

  public ProfileBasicInfo withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ProfileBasicInfo withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ProfileBasicInfo withNativeName(String nativeName) {
    this.nativeName = nativeName;
    return this;
  }

  public ProfileBasicInfo withEmail(String email) {
    this.email = email;
    return this;
  }

  public ProfileBasicInfo withContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
    return this;
  }

  public ProfileBasicInfo withBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public ProfileBasicInfo withCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public enum LocationOfResidence {
    Minsk(42, "Belarus", 10, "Minsk"),
    Yerevan(9, "Armenia", 4, "Yerevan"),
    Petropavlovsk(370, "Kazakhstan", 11, "North Kazakhstan"),
    Vilnius(704, "Lithuania", 58, "Vilniaus apskritis"),
    Gdansk(701, "Poland", 56, "Pomeranian Voivodeship"),
    Saratov(33, "Russia", 9, "Saratovskaya Oblast");

    int cityId;
    String countryName;
    int countryId;
    String regionName;

    LocationOfResidence(int cityId, String countryName, int countryId, String regionName) {
      this.cityId = cityId;
      this.countryName = countryName;
      this.countryId = countryId;
      this.regionName = regionName;
    }

    public int getCityId() {
      return cityId;
    }

    public String getCountryName() {
      return countryName;
    }

    public int getCountryId() {
      return countryId;
    }

    public String getRegionName() {
      return regionName;
    }
  }

  public ProfileBasicInfo withLocationOfResidence(LocationOfResidence locationOfResidence) {
    this.cityId = locationOfResidence.getCityId();
    this.city = locationOfResidence.name();
    this.countryId = locationOfResidence.getCountryId();
    this.country = locationOfResidence.getCountryName();
    this.region = locationOfResidence.getRegionName();
    return this;
  }

  public ProfileBasicInfo withSocialNetworkSkype(String skypeProfile) {
    this.socialNetworkSkype = skypeProfile;
    return this;
  }

  public ProfileBasicInfo withSocialNetworkVk(String vkProfile) {
    this.socialNetworkVk = vkProfile;
    return this;
  }

  public ProfileBasicInfo withSocialNetworkFacebook(String facebookProfile) {
    this.socialNetworkFacebook = facebookProfile;
    return this;
  }

  public ProfileBasicInfo withSocialNetworkTelegram(String telegram) {
    this.socialNetworkTelegram = telegram;
    return this;
  }

  public ProfileBasicInfo withSocialNetworkOdnoklassniki(String odnoklassnikiProfile) {
    this.socialNetworkOdnoklassniki = odnoklassnikiProfile;
    return this;
  }

  public ProfileBasicInfo withSocialNetworkLinkedIn(String linkedInProfile) {
    this.socialNetworkLinkedIn = linkedInProfile;
    return this;
  }

  public ProfileBasicInfo withSocialNetworkGitHub(String gitHubProfile) {
    this.socialNetworkGitHub = gitHubProfile;
    return this;
  }

  @JsonFormat(shape = NUMBER)
  public enum PreferredMethodOfCommunication {
    PHONE, SOCIAL_NETWORK, EMAIL
  }

  public ProfileBasicInfo withPreferredMethodOfCommunication(
      PreferredMethodOfCommunication preferredMethod) {
    this.preferredMethodOfCommunication = preferredMethod;
    return this;
  }

  public ProfileBasicInfo withAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  public ProfileBasicInfo withProfessionalActivities(String professionalActivities) {
    this.professionalActivities = professionalActivities;
    return this;
  }

  public ProfileBasicInfo withPhotoId(int photoId) {
    this.photoId = photoId;
    return this;
  }

  public int getUserId() {
    return userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNativeName() {
    return nativeName;
  }

  public String getEmail() {
    return email;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public LocalDate getCreatedAt() { return createdAt; }

  public int getCityId() {
    return this.cityId;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getSocialNetworkSkype() {
    return socialNetworkSkype;
  }

  public String getSocialNetworkVk() {
    return socialNetworkVk;
  }

  public String getSocialNetworkFacebook() {
    return socialNetworkFacebook;
  }

  public String getSocialNetworkTelegram() {
    return socialNetworkTelegram;
  }

  public String getSocialNetworkOdnoklassniki() {
    return socialNetworkOdnoklassniki;
  }

  public String getSocialNetworkLinkedIn() {
    return socialNetworkLinkedIn;
  }

  public String getSocialNetworkGitHub() {
    return socialNetworkGitHub;
  }

  public PreferredMethodOfCommunication getPreferredMethodOfCommunication() {
    return preferredMethodOfCommunication;
  }

  public String getProfessionalActivities() {
    return professionalActivities;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public int getPhotoId() {
    return photoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileBasicInfo that = (ProfileBasicInfo) o;
    return userId == that.userId && cityId == that.cityId && countryId == that.countryId
        && isCyrillicSymbolsAllowed == that.isCyrillicSymbolsAllowed
        && photoId == that.photoId && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName) && Objects.equals(nativeName,
        that.nativeName) && Objects.equals(email, that.email) && Objects.equals(
        contactPhone, that.contactPhone) && Objects.equals(birthDate, that.birthDate)
        && Objects.equals(createdAt, that.createdAt) && Objects.equals(city,
        that.city) && Objects.equals(country, that.country) && Objects.equals(
        region, that.region) && Objects.equals(socialNetworkSkype, that.socialNetworkSkype)
        && Objects.equals(socialNetworkVk, that.socialNetworkVk)
        && Objects.equals(socialNetworkFacebook, that.socialNetworkFacebook)
        && Objects.equals(socialNetworkTelegram, that.socialNetworkTelegram)
        && Objects.equals(socialNetworkOdnoklassniki, that.socialNetworkOdnoklassniki)
        && Objects.equals(socialNetworkLinkedIn, that.socialNetworkLinkedIn)
        && Objects.equals(socialNetworkGitHub, that.socialNetworkGitHub)
        && preferredMethodOfCommunication == that.preferredMethodOfCommunication
        && Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(
        professionalActivities, that.professionalActivities) && Objects.equals(portfolio,
        that.portfolio) && Objects.equals(staffingDeskId, that.staffingDeskId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, firstName, lastName, nativeName, email, contactPhone, birthDate,
        createdAt, cityId, city, countryId, country, region, socialNetworkSkype,
        socialNetworkVk, socialNetworkFacebook, socialNetworkTelegram, socialNetworkOdnoklassniki,
        socialNetworkLinkedIn, socialNetworkGitHub, preferredMethodOfCommunication,
        isCyrillicSymbolsAllowed, additionalInfo, professionalActivities, photoId, portfolio,
        staffingDeskId);
  }

  @Override
  public String toString() {
    return "ProfileBasicInfo{" +
        "userId=" + userId +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", nativeName='" + nativeName + '\'' +
        ", email='" + email + '\'' +
        ", contactPhone='" + contactPhone + '\'' +
        ", birthDate=" + birthDate +
        ", createdAt=" + createdAt +
        ", cityId=" + cityId +
        ", city='" + city + '\'' +
        ", countryId=" + countryId +
        ", country='" + country + '\'' +
        ", region='" + region + '\'' +
        ", socialNetworkSkype='" + socialNetworkSkype + '\'' +
        ", socialNetworkVk='" + socialNetworkVk + '\'' +
        ", socialNetworkFacebook='" + socialNetworkFacebook + '\'' +
        ", socialNetworkTelegram='" + socialNetworkTelegram + '\'' +
        ", socialNetworkOdnoklassniki='" + socialNetworkOdnoklassniki + '\'' +
        ", socialNetworkLinkedIn='" + socialNetworkLinkedIn + '\'' +
        ", socialNetworkGitHub='" + socialNetworkGitHub + '\'' +
        ", preferredMethodOfCommunication=" + preferredMethodOfCommunication +
        ", isCyrillicSymbolsAllowed=" + isCyrillicSymbolsAllowed +
        ", additionalInfo='" + additionalInfo + '\'' +
        ", professionalActivities='" + professionalActivities + '\'' +
        ", photoId=" + photoId +
        ", portfolio='" + portfolio + '\'' +
        ", staffingDeskId='" + staffingDeskId + '\'' +
        '}';
  }
}
