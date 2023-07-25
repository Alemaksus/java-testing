package com.epmrdpt.bo.hashtag;

public class HashtagFactory {

  public static Hashtag getHashtagWithName(String name) {
    return new Hashtag().withName(name);
  }

  public static Hashtag[] getTwoHashtagWithName(String firstHashtag, String secondHashtag) {
    return new Hashtag[]{
        new Hashtag().withName(firstHashtag),
        new Hashtag().withName(secondHashtag)
    };
  }
}
