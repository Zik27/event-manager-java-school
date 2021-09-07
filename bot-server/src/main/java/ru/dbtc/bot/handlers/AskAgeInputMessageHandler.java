package ru.dbtc.bot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class AskAgeInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private KeyboardMarkUpServices keyboardMarkUpServices;
    private UserProfileData userProfileData;

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
        userProfileData.setName(message.getText());
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askAge");
        Map<String, String> names = new HashMap<>();
        names.put("Есть", "buttonYes18");
        names.put("Нет", "buttonNo18");
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardMarkUpServices.getInlineKeyboardMarkup(names);
        replyToUser.setReplyMarkup(inlineKeyboardMarkup);
        userDataCache.setUserBotState(userId, BotState.ASK_CITY);
        return replyToUser;
    }

}
