package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
@Component
@AllArgsConstructor
public class WaitMarkInputMessageHandler implements InputMessageHandler {
    private UserDataCache userDataCache;

    @Override
    public SendMessage handle(Message message) {
        int userId = message.getFrom().getId();
        userDataCache.setUserBotState(userId, BotState.MAIN_MENU);
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.WAIT_MARK;
    }
}
