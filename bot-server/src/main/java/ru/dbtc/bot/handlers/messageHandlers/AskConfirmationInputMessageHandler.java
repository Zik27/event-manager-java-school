package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserDto;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class AskConfirmationInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private KeyboardMarkUpServices keyboardMarkUpServices;
    private UserProfileData userProfileData;

    @Autowired
    RestTemplate restTemplate;

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
        SendMessage replyToUser;
        String city = message.getText();
        //todo написать проверку есть ли город в бд у роутера
        try {
            //String forObject = restTemplate.getForObject("http://USERS/users/" + userId, String.class);
            userProfileData.setCity(city);
            replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askConfirmation");
            Map<String, String> names = new HashMap<>();
            names.put("Изменить данные", "buttonChangeInfo");
            names.put("Перейти к мероприятиям", "buttonGoToEvent");
            System.out.println("ask confirm handler work");
            InlineKeyboardMarkup inlineKeyboardMarkup = keyboardMarkUpServices.getInlineKeyboardMarkup(names);
            replyToUser.setReplyMarkup(inlineKeyboardMarkup);
            userDataCache.setUserButtonState(userId, Buttons.ASK_CONFIRMATION);
            userDataCache.setUserBotState(userId, BotState.MAIN_MENU);
        } catch (HttpClientErrorException exception) {
            replyToUser = replyMessageService.getReplyMessage(chatId, "К сожалению, в выбранном городе нет мероприятий. Введите другой город");
            userDataCache.setUserBotState(userId, BotState.ASK_CONFIRMATION);
        }
        return replyToUser;
    }
}
