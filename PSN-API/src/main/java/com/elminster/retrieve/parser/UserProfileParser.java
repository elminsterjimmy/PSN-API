package com.elminster.retrieve.parser;

import com.elminster.common.parser.IParser;
import com.elminster.common.parser.ParseException;
import com.elminster.retrieve.data.json.JsonTrophy;
import com.elminster.retrieve.data.json.JsonUserProfile;
import com.elminster.retrieve.data.user.PSNUserProfile;

public class UserProfileParser extends BaseParser implements IParser<JsonUserProfile, PSNUserProfile> {

  @Override
  public PSNUserProfile parse(JsonUserProfile json) throws ParseException {
    if (null == json) {
      return null;
    }
    PSNUserProfile profile = new PSNUserProfile();
    profile.setLevelProcess((byte) json.getProgress());
    profile.setLevel((short) json.getCurLevel());
    profile.setPlusMember(1 == json.getIsPlusUser() ? true : false);
    JsonTrophy jt = json.getTrophies();
    if (null != jt) {
      profile.setTotalBronze(jt.getBronze());
      profile.setTotalGold(jt.getGold());
      profile.setTotalPlatinum(jt.getPlatinum());
      profile.setTotalSilver(jt.getSilver());
    }
    profile.setUserAvatarUrl(parseUrl(json.getAvatarUrl()));
    profile.setUsername(json.getHandle());
    profile.setTotalLevel((short) json.getTotalLevel());
    return profile;
  }

}
