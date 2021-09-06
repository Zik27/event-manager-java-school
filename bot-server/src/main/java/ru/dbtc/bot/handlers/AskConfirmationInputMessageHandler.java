package ru.dbtc.bot.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
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
@Slf4j
@AllArgsConstructor
public class AskConfirmationInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private KeyboardMarkUpServices keyboardMarkUpServices;

    @Override
    public SendMessage handle(Message message) {
        userDataCache.setUserBotState(message.getFrom().getId(), BotState.ASK_CONFIRMATION);
        return processUserInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_CONFIRMATION;
    }

    private SendMessage processUserInput(Message message) {
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        String answer = message.getText();
        //todo написать проверку есть ли город в бд у роутера
        /*if(answer isn't there) {
            SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "К сожалению, в выбранном городе нет мероприятий. Введите другой город");
            userDataCache.setUserBotState(userId, BotState.ASK_CONFIRMATION);*/
        SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askConfirmation");
        Map<String, String> names = new HashMap<>();
        names.put("Изменить данные", "buttonChangeInfo");
        names.put("Перейти к мероприятиям", "buttonGoToEvent");
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardMarkUpServices.getInlineKeyboardMarkup(names);
        replyToUser.setReplyMarkup(inlineKeyboardMarkup);
        userDataCache.setUserBotState(userId, BotState.ASK_NAME);
        return replyToUser;
    }
}
