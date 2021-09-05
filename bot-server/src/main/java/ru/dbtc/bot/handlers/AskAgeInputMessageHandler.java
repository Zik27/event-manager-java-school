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
public class AskAgeInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUserBotState(message.getFrom().getId(), BotState.ASK_AGE);
        return processUserInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_AGE;
    }

    private SendMessage processUserInput(Message message) {
        String answer = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();

        UserProfileData userProfileData = userDataCache.getUserProfileData(userId);
        //BotState botState = userDataCache.getUserBotState(userId);
        userProfileData.setName(answer);
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askAge");
        userDataCache.setUserBotState(userId, BotState.ASK_NAME);
        userDataCache.saveUserProfileData(userId, userProfileData);
        return replyToUser;
    }
}
