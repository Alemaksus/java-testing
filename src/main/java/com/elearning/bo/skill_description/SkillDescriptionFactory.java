package com.epmrdpt.bo.skill_description;

import com.epmrdpt.bo.LanguageEnum;
import com.epmrdpt.bo.skill_information.SkillInformation;
import com.epmrdpt.bo.skill_information.SkillInformationFactory;
import java.util.ArrayList;
import java.util.List;

public class SkillDescriptionFactory {

  public static SkillDescription getGeneratedSkillDescription(int id, String name, boolean status) {
    return new SkillDescription()
        .withId(id)
        .withName(name)
        .withStatus(status)
        .addSkillInformationToList(
            SkillInformationFactory.getGeneratedSkillInformationForCertainLocale(LanguageEnum.EN))
        .addSkillInformationToList(SkillInformationFactory.getGeneratedSkillInformationForCertainLocale(LanguageEnum.RU))
        .addSkillInformationToList(SkillInformationFactory.getGeneratedSkillInformationForCertainLocale(LanguageEnum.UKR));
  }

  public static void editSkillsDescription(SkillDescription skillDescription){
    skillDescription
        .withStatus(!skillDescription.isStatus())
        .withSkillInformations(new ArrayList<SkillInformation>(){{
        add(SkillInformationFactory.getGeneratedSkillInformationForCertainLocale(LanguageEnum.EN));
        add(SkillInformationFactory.getGeneratedSkillInformationForCertainLocale(LanguageEnum.RU));
        add(SkillInformationFactory.getGeneratedSkillInformationForCertainLocale(LanguageEnum.UKR));
        }});
  }

  public static SkillDescription getSkillsDescriptionInGivenLocale(SkillDescription skillDescription, LanguageEnum languageEnum){
    return new SkillDescription()
        .withId(skillDescription.getId())
        .withName(skillDescription.getName())
        .withStatus(skillDescription.isStatus())
        .withLocalizedInfo(skillDescription.getSkillInformationByLanguage(languageEnum));
  }

  public static SkillDescription getSkillDescription(int id, String name, boolean status, List<SkillInformation> skillInformation) {
    return new SkillDescription()
        .withId(id)
        .withStringId(name)
        .withName(name)
        .withStatus(status)
        .withSkillInformations(skillInformation);
  }
  public static SkillDescription getSkillDescriptionWithLocalized(int id, String name, boolean status, SkillInformation skillInformation) {
    return new SkillDescription()
        .withId(id)
        .withStringId(name)
        .withName(name)
        .withStatus(status)
        .withLocalizedInfo(skillInformation);
  }
}
