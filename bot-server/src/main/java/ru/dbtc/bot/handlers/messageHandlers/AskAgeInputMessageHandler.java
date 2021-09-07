package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.util.HashMap;
import java.util.Map;

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
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askAge");
        userDataCache.setUserBotState(userId, BotState.ASK_CITY);
        userDataCache.setUserButtonState(userId, Buttons.ASK_CONFIRMATION);
        return replyToUser;
    }

}
