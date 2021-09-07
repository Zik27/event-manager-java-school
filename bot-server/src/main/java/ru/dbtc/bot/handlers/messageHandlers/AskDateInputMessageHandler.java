package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.ReplyMessageService;

@Component
@AllArgsConstructor
public class AskDateInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askDate");
        userDataCache.setUserBotState(userId, BotState.CHOOSE_EVENT);
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_DATE;
    }
}
