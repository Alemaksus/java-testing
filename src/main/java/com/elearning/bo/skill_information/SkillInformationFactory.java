package com.epmrdpt.bo.skill_information;

import com.epmrdpt.bo.Fact;
import com.epmrdpt.bo.LanguageEnum;
import org.apache.commons.lang3.RandomStringUtils;

public class SkillInformationFactory {

  public static SkillInformation getGeneratedSkillInformationForCertainLocale(
      LanguageEnum languageEnum) {
    return new SkillInformation()
        .withLanguage(languageEnum)
        .withTitle(RandomStringUtils.randomAlphanumeric(10))
        .withDescription(RandomStringUtils.randomAlphanumeric(20))
        .addFactToList(new Fact().withTitle(RandomStringUtils.randomNumeric(1)).withDescription(RandomStringUtils.randomAlphanumeric(10)));
  }

  public static SkillInformation getSkillInformationWithFactForCertainLocale(String skillInformationTitle,
      String skillInformationDescription, String factTitle,
      String factDescription, LanguageEnum languageEnum) {
    return new SkillInformation()
        .withLanguage(languageEnum)
        .withTitle(skillInformationTitle)
        .withDescription(skillInformationDescription)
        .addFactToList(new Fact().withTitle(factTitle).withDescription(factDescription));
  }
}
