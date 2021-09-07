package ru.dbtc.bot.handlers.buttonHandlers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dbtc.bot.MainMenu;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

@Component
@AllArgsConstructor
public class ChooseEventButtonHandler implements ButtonHandler {
    private UserDataCache userDataCache;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public SendMessage handle(CallbackQuery buttonQuery) {
        Message message = buttonQuery.getMessage();
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        SendMessage replyToUser;
        //String forObject = restTemplate.getForObject("http://USERS/users/" + userId, String.class);
        if (buttonQuery.getData().equals("buttonCinema")) {
            replyToUser = new SendMessage(chatId, "cinema event created");
            //todo обратиться в роутер с категорией и выдать пользователю json
        } else if (buttonQuery.getData().equals("buttonSport")) {
            //todo отправить запрос
            replyToUser = new SendMessage(chatId, "sport event created");
            /*Post postToInsert = Post.builder()
                    .firstName(buttonQuery.getMessage().getFrom().getFirstName())
                    .lastName(buttonQuery.getMessage().getFrom().getLastName())
                    .userId(userId)
                    .user(buttonQuery.getMessage().getFrom().getUserName())
                    .age(userDataCache.getUserProfileData(userId).getAge())
                    .build();
            final Post insertedPost = restTemplate.postForObject("http://USERS/users/", postToInsert, Post.class);
            System.out.println(insertedPost);*/
            userDataCache.setUserBotState(userId, BotState.MAIN_MENU);
            //callBackAnswer.setReplyMarkup(mainMenu.getMainMenuKeyboard());
        } else if (buttonQuery.getData().equals("buttonFestival")) {
            replyToUser = new SendMessage(chatId, "festival event created");
        } else {
            //todo вытащить город из роутера(??)
            replyToUser = new SendMessage(chatId, "random event created");
            //String forObject = restTemplate.getForObject("http://EVENT-DOWNLOADER/random" + userId, String.class);
        }
        userDataCache.setUserBotState(userId, BotState.MARK);
        return replyToUser;
    }

    @Override
    public Buttons getHandlerName() {
        return Buttons.CHOOSE_EVENT;
    }
}
