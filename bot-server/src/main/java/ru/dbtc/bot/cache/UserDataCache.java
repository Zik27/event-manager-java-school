package ru.dbtc.bot.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class UserDataCache implements DataCache {
    //private Cache<Integer, BotState> usersBotState = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build();
    private Map<Integer, BotState> usersBotState = new HashMap<>();
    private Cache<Integer, UserProfileData> usersProfileData = CacheBuilder.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).build();
    private Map<Integer, Buttons> usersButtonState = new HashMap<>();

    @Override
    public void setUserBotState(Integer userId, BotState botState) {
        usersBotState.put(userId, botState);
    }

    @Override
    public void setUserButtonState(Integer userId, Buttons button) {
        usersButtonState.put(userId, button);
    }

    @SneakyThrows
    @Override
    public BotState getUserBotState(Integer userId) {
        BotState botState = usersBotState.get(userId);
        if (botState == null) {
            botState = BotState.ASK_AGE;
        }
        return botState;
    }

    @Override
    public Buttons getUserButtonState(Integer userId) {
        Buttons button = usersButtonState.get(userId);
        if (button == null) {
            button = Buttons.ASK_AGE;
        }
        return button;
    }

    @SneakyThrows
    @Override
    public UserProfileData getUserProfileData(Integer userId) {
        return usersProfileData.get(userId, UserProfileData::new);
    }

    @Override
    public void saveUserProfileData(Integer userId, UserProfileData userProfileData) {
        usersProfileData.put(userId, userProfileData);
    }
}
