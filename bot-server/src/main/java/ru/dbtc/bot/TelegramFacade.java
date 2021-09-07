package ru.dbtc.bot;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserDto;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class TelegramFacade {
    private BotStateContext botStateContext;
    private ButtonContext buttonContext;
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;

    @Autowired
    private final RestTemplate restTemplate;

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;
        if (update.hasCallbackQuery()) {
            System.out.println("есть кнопочки");
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
        System.out.println("process handle input work");
        BotState botState;
        Buttons buttons;
        String answer = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();

        switch (answer) {
            case "/start":
                try {
                    String forObject = restTemplate.getForObject("http://USERS/users/" + userId, String.class);
                    botState = BotState.MAIN_MENU;
                } catch (HttpClientErrorException exception) {
                    System.out.println("beginnnnnnnnnnnnnnnnnn");
                    /*Post postToInsert = UserDto.builder()
                        .firstName(message.getFrom().getFirstName())
                        .lastName(message.getFrom().getLastName())
                        .id(userId)
                        .userName(userName)
                        .age(12)
                        .build();*/
                    /*UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://USERS/users")
                            .queryParam("id", userId)
                            .queryParam("userName", userName)
                            .queryParam("firstName", firstName)
                            .queryParam("lastName", lastName)
                            .queryParam("age", 12);

                    UserDto insertedPost = restTemplate.postForObject(builder.toUriString(), null , UserDto.class);
                    System.out.println(insertedPost);*/
                    botState = BotState.ASK_AGE;
                }
                break;
            case "Найти новое мероприятие":
                botState = BotState.ASK_DATE;
                break;
            case "Мои мероприятия":
                botState = BotState.MAIN_MENU;
                //todo выгрузить из event_history
                //String forObject = restTemplate.getForObject("http://HISTORY/history" + userId, String.class);
                System.out.println("всееееееееееееееееееееееееее мероприятия");
                break;
            case "Изменить информацию о себе":
                SendMessage replyToUser = replyMessageService.getReplyMessage(chatId, "reply.askAge");
                userDataCache.setUserBotState(userId, BotState.ASK_CITY);
                userDataCache.setUserButtonState(userId, Buttons.ASK_CONFIRMATION);
                return replyToUser;
            case "Помощь":
                botState = BotState.MAIN_MENU;
                break;
            default:
                botState = userDataCache.getUserBotState(userId);
                System.out.println("всееееееееееееееееееееееееее мероприятия");
                break;
        }
        System.out.println(botState);
        userDataCache.setUserBotState(userId, botState);
        return botStateContext.processInputMessage(botState, message);
    }
    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        System.out.println("process call back work");
        final int userId = buttonQuery.getFrom().getId();
        Buttons button = userDataCache.getUserButtonState(userId);
        return buttonContext.processButton(button, buttonQuery);
    }
}
