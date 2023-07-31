package com.apple_insider;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/*
 * The Home Page of the site: Appleinsider.ru
 * */

public class HomePage {
  private final SelenideElement textBoxInput = $x("//input[@type='text']");

  public HomePage(String url){
    Selenide.open(url);
  }

  public SearchPage search(String searchString){
    textBoxInput.setValue(searchString);
    textBoxInput.sendKeys(Keys.ENTER);
    return new SearchPage();
  }

}
