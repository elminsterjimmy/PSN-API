package com.elminster.retrieve.service.test;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.elminster.retrieve.data.user.PSNUserTrophy;
import com.elminster.retrieve.data.user.PSNUserGame;
import com.elminster.retrieve.data.user.PSNUserProfile;
import com.elminster.retrieve.service.IPSNApi;
import com.elminster.retrieve.service.PSNApiImpl;
import com.elminster.retrieve.util.SystemSetting;

public class PSNApiTest {
  
  IPSNApi api = new PSNApiImpl();

  @Ignore
  @Test
  public void testRetrieveUserProfile() throws Exception {
    String psnUser = "truebeibeiking";
    PSNUserProfile profile = api.getPSNUserProfile(psnUser);
    System.out.println(profile);
  }
  
  @Ignore
  @Test
  public void testRetrieveUserGameList() throws Exception {
    String psnUser = "Roughdawg4";
    List<PSNUserGame> gameList = api.getPSNUserGameList(psnUser);
    System.out.println(gameList);
  }
  
  @Ignore
  @Test
  public void testRetrieveUserGameAchieves() throws Exception {
    String[] gameIds = new String[1];
    gameIds[0] = "NPWR08326_00";
    String xblUsername = "Roughdawg4";
    for (String gameId : gameIds) {
      System.out.println("============================================================");
      List<PSNUserTrophy> achieves = api.getPSNUserGameTrophies(xblUsername, gameId);
      System.out.println(achieves);
    }
  }
  
  @AfterClass
  public static void done() throws IOException {
    // persist the api last called time.
    SystemSetting.INSTANCE.persist();
  }
}
