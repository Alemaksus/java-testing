package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_STUDENT_GROUP_CONTROLLER_STUDENT_GROUPS_LIST;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106881 Verify 'GET /api/training-management/{planId}/classes/{classId}/student-groups/list' endpoint",
    groups = {"api"})
public class GetClassStudentGroupControllerStudentsGroupsListEndpointApiTest {

  private final int planId = 2857;
  private final int classId = 4021;
  private final int pageNumber = 1;
  private final int itemsPerPage = 10;
  private final List<String> expectedStudentGroupNamesList = Arrays.asList(
      "AutoTestGroupd",
      "AutoTestLearningGroup");
  private final int invalidPlanId = 888;
  private final int invalidClassId = 12;
  private final String invalidPageNumber = "pageNumber";
  private final String invalidItemsPerPage = "itemsPerPage";
  private SoftAssert softAssert;

  @DataProvider(name = "Provider data for test with path and query param")
  private Object[][] dataProviderWithParam() {
    return new Object[][]{
        {invalidPlanId, classId, pageNumber, itemsPerPage},
        {planId, invalidClassId, pageNumber, itemsPerPage},
        {planId, classId, invalidPageNumber, itemsPerPage},
        {planId, classId, pageNumber, invalidItemsPerPage}
    };
  }

  @Test(priority = 1)
  public void verifyGetStudentGroupsStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getStudentGroupsList(planId, classId, pageNumber,
        itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<String> actualStudentGroupNamesList = jsonPath.get("Items.Name");
    softAssert.assertEquals(actualStudentGroupNamesList, expectedStudentGroupNamesList,
        "Actual student group names list is not equal to expected student group names list!");
    softAssert.assertEquals(jsonPath.getInt("CurrentPageNumber"), pageNumber,
        "Actual page number is not equal to expected page number!");
    softAssert.assertEquals(jsonPath.getInt("ItemsPerPage"), itemsPerPage,
        "Actual items per page is not equal to expected items per page!");
    softAssert.assertAll();
  }

  @Test(priority = 1, dataProvider = "Provider data for test with path and query param")
  public void verifyGetStudentGroupsBadRequest(int planId, int groupId, Object pageNumber,
      Object itemsPerPage) {
    getStudentGroupsList(planId, groupId, pageNumber, itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetStudentGroupsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStudentGroupsList(planId, classId, pageNumber, itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStudentGroupsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStudentGroupsList(planId, classId, pageNumber, itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStudentGroupsList(int planId, int classId, Object pageNumber,
      Object itemsPerPage) {
    return given()
        .pathParam("planId", planId)
        .pathParam("classId", classId)
        .queryParam("pageNumber", pageNumber)
        .queryParam("itemsPerPage", itemsPerPage)
        .when()
        .get(TRAINING_MANAGEMENT_STUDENT_GROUP_CONTROLLER_STUDENT_GROUPS_LIST);
  }
}
