package ru.dbtc.bot.cache;

import org.springframework.stereotype.Component;
import ru.dbtc.bot.constants.BotState;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCache {
    private Map<Integer, BotState> usersBotState = new HashMap<>();
    private Map<Integer, UserProfileData> usersProfileData = new HashMap<>();
    @Override
    public void setUserBotState(int userId, BotState botState) {
        usersBotState.put(userId, botState);
    }

    @Override
    public BotState getUserBotState(int userId) {
        BotState botState = usersBotState.get(userId);
        if (botState == null) {
            botState = BotState.ASK_NAME;
        }
        return botState;
    }

    @Override
    public UserProfileData getUserProfileData(int userId) {
        UserProfileData userProfileData = usersProfileData.get(userId);
        if(userProfileData == null) {
            userProfileData = new UserProfileData();
        }
        return userProfileData;
    }

    @Override
    public void saveUserProfileData(int userId, UserProfileData userProfileData) {
        usersProfileData.put(userId, userProfileData);
    }
}
