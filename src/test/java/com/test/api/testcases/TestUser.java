package com.test.api.testcases;

import java.io.IOException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.test.api.config.Base;
import com.test.api.config.BaseUrl;
import com.test.api.function.Token;
import com.test.api.function.Validation;
import com.test.api.utility.ReadExcel;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TestUser extends Base {

  public String path = "../api-hot/src/test/java/com/test/api/datatest/data.xlsx";

  @DataProvider
  public String[][] User() throws InvalidFormatException, IOException {
    ReadExcel readExcel = new ReadExcel();
    return readExcel.getCellData(path, "User");
  }

  @Severity(SeverityLevel.CRITICAL)
  @Description("Get My Profile")
  @Test(priority = 0, testName = "Get My Profile", dataProvider = "User")
  public void myProfile(String username, String password) {
    Token token = new Token();
    BaseUrl baseUrl = new BaseUrl();
    Validation validation = new Validation();

    rs.baseUri(baseUrl.urlUgcVote("/v1/user/my-profile"))
        .headers("Authorization", token.login(username, password, "1234", "android")).when().get().then()
        .statusCode(200).log().all();

    validation.getMessageClientSuccess(rs);
    validation.GetNotNull("data.user_id", rs);
    validation.GetNotNull("data.role", rs);
    validation.GetNotNull("data.total_followers", rs);
    validation.GetNotNullOr("data.email", "data.phone", rs);
  }


}