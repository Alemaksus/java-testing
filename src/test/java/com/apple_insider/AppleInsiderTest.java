package com.apple_insider;

import com.core.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class AppleInsiderTest extends BaseTest {

  private final static String BASE_URL = "https://appleinsider.ru/";
  private final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
  private final static String EXPECTED_WORD = "iphone-13";

  @Test
  public void checkUrl() {
    Assert.assertTrue(new HomePage(BASE_URL)
        .search(SEARCH_STRING)
        .getHrefFromFirstArticle()
        .contains(EXPECTED_WORD));
  }

}
