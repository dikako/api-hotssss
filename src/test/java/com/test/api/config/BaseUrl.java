package com.test.api.config;

public class BaseUrl {
  String baseUrlCore = "https://rc-api.rctiplus.com/api";
  String baseUrlUgcVote = "https://rc-api.rctiplus.com/ugc-vote/api";

  public String urlCore(String slash) {
    return baseUrlCore + slash;
  }

  public String urlUgcVote(String slash) {
    return baseUrlUgcVote + slash;
  }
}