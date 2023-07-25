package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_AUTO_REPLY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_DAY_LEFT_BEFORE_EVENT_START_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_CANCELLED_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_COMING_SOON_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_RECORDING_AVAILABLE_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.LanguageEnum;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-111024 Verify 'GET /api/event-management/autoreply' endpoint",
    groups = {"api"})
public class GetEventManagementControllerAutoReplyEndpointApiTest {

  private SoftAssert softAssert;

  private final List<String> expectedAutoReplyLinesList = Arrays.asList(
      "Your application for participation is pending<Event Name>",
      "Unfortunately, your participation has not been approved by event organizers.",
      "Stay tuned to our news and explore other opportunities!",
      "Ваша заявка на участие рассматривается<Event Name>",
      "К сожалению, организаторы мероприятия отклонили вашу заявку на участие.",
      "Следите за нашими новостями и исследуйте другие возможности!",
      "Ваша заявка на участь розглядається<Event Name>",
      "На жаль, ваша заявка на участь була відхилена організаторами заходу.",
      "Слідкуйте за нашими новинами та відкривайте для себе інші цікаві можливості.");


  @Test(priority = 1)
  public void verifyGetAutoReplyStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getAutoReplyActive()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<List<String>> actualAutoReplySubjectsList = jsonPath.get(
        "AutoReplyTabInfoDtos.AutoReplyTabDefaultMessagesInfo.Subject");
    List<List<List<String>>> actualAutoReplyLinesList = jsonPath.get(
        "AutoReplyTabInfoDtos.AutoReplyTabDefaultMessagesInfo.Lines");
    softAssert.assertTrue(
        actualAutoReplySubjectsList.stream().flatMap(Collection::stream)
            .collect(Collectors.toList())
            .containsAll(getAutoReplySubjectsList()),
        "Subjects list doesn't contain all templates!");
    softAssert.assertTrue(
        actualAutoReplyLinesList.stream().flatMap(Collection::stream).flatMap(Collection::stream)
            .collect(Collectors.toList()).containsAll(expectedAutoReplyLinesList),
        "AutoReply Lines list doesn't contain all expected AutoReply Lines!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetAutoReplyUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getAutoReplyActive()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetAutoReplyForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getAutoReplyActive()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getAutoReplyActive() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_AUTO_REPLY);
  }

  private List<String> getAutoReplySubjectsList() {
    List<String> autoReplySubjectsList = new ArrayList<>();
    for (LanguageEnum language : LanguageEnum.values()) {
      autoReplySubjectsList.add(
          LocaleProperties.getValueOf(
              REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE,
              language));
      autoReplySubjectsList.add(
          LocaleProperties.getValueOf(
              REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_DAY_LEFT_BEFORE_EVENT_START_TEMPLATE,
              language));
      autoReplySubjectsList.add(
          LocaleProperties.getValueOf(
              REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_COMING_SOON_TEMPLATE,
              language));
      autoReplySubjectsList.add(
          LocaleProperties.getValueOf(
              REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_CANCELLED_TEMPLATE,
              language));
      autoReplySubjectsList.add(
          LocaleProperties.getValueOf(
              REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_RECORDING_AVAILABLE_TEMPLATE,
              language));
    }
    return autoReplySubjectsList;
  }
}
