package com.test.api.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.test.api.config.Base;
import com.test.api.config.BaseUrl;
import com.test.api.function.Token;
import com.test.api.function.Validation;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;

public class TestVoteCompetition extends Base {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Post Vote")
    @Test(priority = 1, testName = "Post Vote")
    public void vote() {
      Token token = new Token();
      BaseUrl baseUrl = new BaseUrl();
      Validation validation = new Validation();

      Integer quota = Integer.valueOf(token.quotaVoteDefault());


      if (quota == 0 && quota > 3) {
          System.out.println("Sisa Quota Anda: " + quota);
        }  else {
          for (int i = 0; i < quota; i++) {
            System.out.println("Sisa Quota Ramaining Anda: " + quota);
            Integer competitionId = Integer.valueOf(token.competitionId());
            Integer videoId = Integer.valueOf(token.videoId());
            Integer userId = Integer.valueOf(token.id("ahmadilham000@gmail.com", "01101994", "1234", "android"));
        
            Map<String, Object> vote = new HashMap<String, Object>();
            vote.put("competition_id", competitionId);
            vote.put("contestant_id", userId);
            vote.put("vote_number", 1);
            vote.put("video_id", videoId);
        
            List<Map<String, Object>> json = new ArrayList<Map<String, Object>>();
            json.add(vote);
        
            rs.baseUri(baseUrl.urlUgcVote("/v1/vote/competition")).contentType(ContentType.JSON)
                .headers("Authorization", token.login("paijo@mailinator.com", "dikakoko", "1234", "android")).body(vote).log()
                .all().then().statusCode(200).log().all();
    
            int quotaRemaining = quota - 1;
            String quotaRemainingString = String.valueOf(quotaRemaining);
        
            validation.postMessageClientSuccess(rs);  
            validation.getBody("data.remaining_quota", rs, quotaRemainingString);
        }
      } 
      }
      }   