package ru.dbtc.bot.handlers.buttonHandlers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.services.ReplyMessageService;

@Component
@AllArgsConstructor
public class AskSubscribeButtonHandler implements ButtonHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public SendMessage handle(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        SendMessage callBackAnswer = new SendMessage();
        if (buttonQuery.getData().equals("buttonSubscribe")) {
            //todo загрузить в event_history
            //String forObject = restTemplate.putForObject("http://HISTORY/history" + userId, String.class);
            callBackAnswer = new SendMessage(chatId, "Уверен, что хочешь подписаться?");
            userDataCache.setUserBotState(userId, BotState.WAIT_MARK);
        } else if (buttonQuery.getData().equals("buttonNotSubscribe")) {
            userDataCache.setUserBotState(userId, BotState.MAIN_MENU);
        }
        return callBackAnswer;
    }

    @Override
    public Buttons getHandlerName() {
        return Buttons.ASK_SUBSCRIBE;
    }
}
