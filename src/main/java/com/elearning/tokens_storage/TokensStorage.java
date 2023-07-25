package com.epmrdpt.tokens_storage;

import static io.restassured.RestAssured.requestSpecification;

import io.restassured.internal.RequestSpecificationImpl;

public class TokensStorage {

  private TokensStorage() {
  }

  private static final String BEARER = "Bearer ";
  private static String adminLoginToken;
  private static String studentLoginToken;
  private static String adminAuthorizationToken;
  private static String studentAuthorizationToken;
  private static boolean isAdminRequestSpecificationSet = false;

  public static String getAdminLoginToken() {
    return adminLoginToken;
  }

  public static String getAdminAuthorizationToken() {
    return adminAuthorizationToken;
  }

  public static String getStudentAuthorizationToken() {
    return studentAuthorizationToken;
  }

  public static boolean isAdminRequestSpecificationSet() {
    return isAdminRequestSpecificationSet;
  }

  /**
   * Store SuperAdmin and LearningStudent tokens in the storage
   */
  public static void setTokens(String adminToken, String adminAccessToken, String studentToken,
      String studentAccessToken) {
    adminLoginToken = adminToken;
    adminAuthorizationToken = adminAccessToken;
    studentLoginToken = studentToken;
    studentAuthorizationToken = studentAccessToken;
  }

  /**
   * Store SuperAdmin tokens in the storage
   */
  public static void setAdminCredentials(String adminToken, String accessToken) {

    adminLoginToken = adminToken;
    adminAuthorizationToken = accessToken;
    isAdminRequestSpecificationSet = true;
  }

  /**
   * Set up LearningStudent credentials to the request specification
   */
  public static void setUpStudentRestAssuredCredentials() {
    ((RequestSpecificationImpl) requestSpecification)
        .replaceHeader("x-csrf-token", studentLoginToken);
    ((RequestSpecificationImpl) requestSpecification)
        .replaceHeader("Authorization", BEARER + studentAuthorizationToken);
  }

  /**
   * Set up SuperAdmin credentials to the request specification
   */
  public static void setUpAdminRestAssuredCredentials() {
    ((RequestSpecificationImpl) requestSpecification)
        .replaceHeader("x-csrf-token", adminLoginToken);
    ((RequestSpecificationImpl) requestSpecification)
        .replaceHeader("Authorization", BEARER + adminAuthorizationToken);
  }
}
