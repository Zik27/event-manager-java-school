package ru.dbtc.bot.cache;

import ru.dbtc.bot.constants.BotState;

public interface DataCache {
    //todo разбить на несколько интерфейсов
    void setUserBotState(int userId, BotState botState);
    BotState getUserBotState(int userId);
    UserProfileData getUserProfileData(int userId);
    void saveUserProfileData(int userId, UserProfileData userProfileData);
}
