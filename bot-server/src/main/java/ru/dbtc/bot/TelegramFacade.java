package ru.dbtc.bot;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.KeyboardMarkUpServices;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private MainMenu mainMenu;

    @Autowired
    private final RestTemplate restTemplate;

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return processCallbackQuery(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        BotState botState;
        String answer = message.getText();
        int userId = message.getFrom().getId();
        if (answer.equals("/start")) {
            String forObject = restTemplate.getForObject("http://USERS/users/" + userId, String.class);
            System.out.println(forObject);
            //todo посмотреть в user service есть ли такой юзер
            //if (user isn't there'){
            botState = BotState.ASK_NAME;
            //else botState = BotState.CHOOSE_EVENT;
        } else {
            botState = userDataCache.getUserBotState(userId);
        }
        userDataCache.setUserBotState(userId, botState);
        return botStateContext.processInputMessage(botState, message);
    }
    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        KeyboardMarkUpServices keyboardMarkUpServices = new KeyboardMarkUpServices();
        Message message = buttonQuery.getMessage();
        BotState botState;
        BotApiMethod<?> callBackAnswer = mainMenu.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");

        if (buttonQuery.getData().equals("buttonYes18")) {
            callBackAnswer = new SendMessage(chatId, "Какой город тебя интересует?");
            userDataCache.setUserBotState(userId, BotState.ASK_CONFIRMATION);
        } else if (buttonQuery.getData().equals("buttonNo18")){
            //todo фильтрация для детей
            callBackAnswer = new SendMessage(chatId, "Какой город тебя интересует?");
            userDataCache.setUserBotState(userId, BotState.ASK_CONFIRMATION);
        } else if (buttonQuery.getData().equals("buttonChangeInfo")) {
            callBackAnswer = new SendMessage(chatId, "Как тебя зовут?");
            userDataCache.setUserBotState(userId, BotState.ASK_AGE);
        } else if(buttonQuery.getData().equals("buttonGoToEvent")) {
            userDataCache.setUserBotState(userId, BotState.CHOOSE_EVENT);
            callBackAnswer = mainMenu.getMainMenuMessage(chatId, "Для выбора мероприятия воспользуйтесь главным меню");
        }
        return callBackAnswer;


    }
}
