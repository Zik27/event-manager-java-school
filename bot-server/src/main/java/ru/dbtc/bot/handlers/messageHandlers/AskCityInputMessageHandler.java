package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserDto;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.ReplyMessageService;

@Component
@AllArgsConstructor
public class AskCityInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private  UserProfileData userProfileData;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUserBotState(message.getFrom().getId(), BotState.ASK_CITY);
        return processUserInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_CITY;
    }

    private SendMessage processUserInput(Message message) {
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        int age = Integer.parseInt(message.getText());
        userProfileData.setAge(age);
        UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askCity");
        userDataCache.setUserBotState(userId, BotState.ASK_CONFIRMATION);
        userDataCache.saveUserProfileData(userId, userProfileData);
        return replyToUser;
    }

}
