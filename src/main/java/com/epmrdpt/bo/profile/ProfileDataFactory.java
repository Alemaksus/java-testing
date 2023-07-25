package com.epmrdpt.bo.profile;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import com.epmrdpt.bo.profile.ProfileBasicInfo.LocationOfResidence;
import com.epmrdpt.bo.profile.ProfileBasicInfo.PreferredMethodOfCommunication;
import com.epmrdpt.utils.RandomUtils;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDateTime;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class ProfileDataFactory {

  private static final Random RANDOM_GENERATOR = new Random();
  private static final String CURRENT_TIMESTAMP = LocalDateTime.now().format(ISO_LOCAL_DATE_TIME);

  public static Contacts getGeneratedContacts() {
    return new Contacts()
        .withEmail(StringUtils.getGeneratedEmail())
        .withContactPhone(RandomStringUtils.randomNumeric(10))
        .withSkype(RandomStringUtils.randomAlphanumeric(10))
        .withAllowShowingContacts(RANDOM_GENERATOR.nextBoolean());
  }

  public static ProfileBasicInfo getGeneratedProfileBasicInfo() {
    return new ProfileBasicInfo()
        .withId(RANDOM_GENERATOR.nextInt())
        .withFirstName("ApiTest" + RandomStringUtils.randomAlphabetic(5, 8))
        .withLastName(RandomStringUtils.randomAlphabetic(5, 12))
        .withNativeName(RandomStringUtils.randomAlphabetic(12, 20))
        .withEmail(RandomStringUtils.randomAlphanumeric(15, 20))
        .withContactPhone(RandomStringUtils.randomNumeric(10))
        .withBirthDate(RandomUtils.getRandomDate(1960, 2000))
        .withCreatedAt(RandomUtils.getRandomDate(2015, 2022))
        .withLocationOfResidence(RandomUtils.getRandomEnumValue(LocationOfResidence.class))
        .withPreferredMethodOfCommunication(RandomUtils.getRandomEnumValue(
            PreferredMethodOfCommunication.class))
        .withSocialNetworkSkype(RandomStringUtils.randomAlphanumeric(10, 20) + CURRENT_TIMESTAMP)
        .withSocialNetworkGitHub(RandomStringUtils.randomAlphanumeric(10, 20) + CURRENT_TIMESTAMP)
        .withSocialNetworkTelegram(RandomStringUtils.randomAlphanumeric(10, 20) + CURRENT_TIMESTAMP)
        .withAdditionalInfo(RandomStringUtils.randomAlphanumeric(10, 30) + CURRENT_TIMESTAMP);
  }
}
