package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class ChooseEventInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private KeyboardMarkUpServices keyboardMarkUpServices;

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        String answer = message.getText();
        Date dateEvent;
        SendMessage replyToUser;
        try {
            dateEvent = new SimpleDateFormat("yyyy-mm-dd").parse(answer);
            //todo отправить в ивенты дату
            replyToUser = replyMessageService.getReplyMessage(chatId, "reply.chooseEvent");
            Map<String, String> names = new HashMap<>();
            names.put("Кино", "buttonCinema");
            names.put("Другое", "buttonOtherEvent");
            names.put("Фестиваль", "buttonFestival");
            names.put("Спорт", "buttonSport");
            InlineKeyboardMarkup inlineKeyboardMarkup = keyboardMarkUpServices.getInlineKeyboardMarkup(names);
            replyToUser.setReplyMarkup(inlineKeyboardMarkup);
            userDataCache.setUserBotState(userId, BotState.SUBSCRIBE);
        } catch (ParseException e) {
            replyToUser = replyMessageService.getReplyMessage(chatId, "reply.wrongDate");
            userDataCache.setUserBotState(userId, BotState.CHOOSE_EVENT);
        }
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CHOOSE_EVENT;
    }
}
