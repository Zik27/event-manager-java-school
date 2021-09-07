package ru.dbtc.bot.cache;

import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;

public interface DataCache {
    void setUserBotState(Integer userId, BotState botState);
    void setUserButtonState(Integer userId, Buttons button);
    BotState getUserBotState(Integer userId);
    Buttons getUserButtonState(Integer userId);
    UserProfileData getUserProfileData(Integer userId);
    void saveUserProfileData(Integer userId, UserProfileData userProfileData);
}
