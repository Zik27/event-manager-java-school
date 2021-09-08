package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.util.HashMap;
import java.util.Map;
@Component
@AllArgsConstructor
public class SubscribeInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private KeyboardMarkUpServices keyboardMarkUpServices;

    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askSubscribe");
        Map<String, String> names = new HashMap<>();
        names.put("Подписаться на это событие", "buttonSubscribe");
        names.put("Поискать еще", "buttonNotSubscribe");
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardMarkUpServices.getInlineKeyboardMarkup(names);
        replyToUser.setReplyMarkup(inlineKeyboardMarkup);
        userDataCache.setUserButtonState(userId, Buttons.ASK_SUBSCRIBE);
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SUBSCRIBE;
    }
}
