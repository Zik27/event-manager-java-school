package ru.dbtc.bot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.ReplyMessageService;

@Component
@AllArgsConstructor
public class AskCityInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private  UserProfileData userProfileData;

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
        //todo внести данные в бд User_service
        String answer = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        userProfileData.setAge(Integer.parseInt(message.getText()));
        UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
        userProfileData.setName(answer);
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askCity");
        userDataCache.setUserBotState(userId, BotState.ASK_CONFIRMATION);
        userDataCache.saveUserProfileData(userId, userProfileData);
        return replyToUser;
    }

}
