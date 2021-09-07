package ru.dbtc.bot.handlers.buttonHandlers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.cache.UserDto;
import ru.dbtc.bot.cache.UserProfileData;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

@Component
@AllArgsConstructor
public class ConfirmationButtonHandler implements ButtonHandler {
    @Autowired
    private final RestTemplate restTemplate;
    private KeyboardMarkUpServices keyboardMarkUpServices;
    private ReplyMessageService replyMessageService;
    private UserProfileData userProfileData;

    private UserDataCache userDataCache;
    @Override
    public SendMessage handle(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        SendMessage callBackAnswer = new SendMessage();
        callBackAnswer.setChatId(chatId);
        if (buttonQuery.getData().equals("buttonChangeInfo")) {
            callBackAnswer = replyMessageService.getReplyMessage(chatId, "reply.askAge");
            userDataCache.setUserBotState(userId, BotState.ASK_CITY);
            userDataCache.setUserButtonState(userId, Buttons.ASK_CONFIRMATION);
        } else if (buttonQuery.getData().equals("buttonGoToEvent")) {
            //todo check
            /*UserDto dtoToInsert = UserDto.builder()
                        .firstName(buttonQuery.getMessage().getFrom().getFirstName())
                        .lastName(buttonQuery.getMessage().getFrom().getLastName())
                        .id(userId)
                        .userName(buttonQuery.getMessage().getFrom().getUserName())
                        .birthYear(userProfileData.getAge())
                        .city(userProfileData.getCity())
                        .build();
            UserDto insertedPost = restTemplate.postForObject("http://USERS/users", dtoToInsert, UserDto.class);*/
            userDataCache.setUserBotState(userId, BotState.MAIN_MENU);
            callBackAnswer = new SendMessage(chatId, "Готов вернуться к основному меню?");
            //callBackAnswer.setReplyMarkup(mainMenu.getMainMenuKeyboard());
        }
        return callBackAnswer;
    }

    @Override
    public Buttons getHandlerName() {
        return Buttons.ASK_CONFIRMATION;
    }

}
