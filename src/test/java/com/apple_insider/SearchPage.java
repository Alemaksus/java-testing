package com.apple_insider;

import static com.codeborne.selenide.Selenide.$$x;

import com.codeborne.selenide.ElementsCollection;

public class SearchPage {

  private final ElementsCollection articleTitles = $$x("//h2//a");

  public String getHrefFromFirstArticle() {
    return articleTitles.first().getAttribute("href");

  }

}
