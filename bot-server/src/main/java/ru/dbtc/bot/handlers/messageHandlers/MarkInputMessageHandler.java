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
public class MarkInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;

    @Override
    public SendMessage handle(Message message) {
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askMark");
        userDataCache.setUserBotState(userId, BotState.WAIT_MARK);
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MARK;
    }
}
