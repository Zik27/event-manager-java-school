package ru.dbtc.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.handlers.InputMessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new ConcurrentHashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler->this.messageHandlers.put(handler.getHandlerName(), handler));
    }


    public SendMessage processInputMessage(BotState botState, Message message) {
        InputMessageHandler currentHandler = findMessageHandler(botState);
        return currentHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState botState) {
        return messageHandlers.get(botState);
    }
}
