package ru.dbtc.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.dbtc.bot.constants.Buttons;
import ru.dbtc.bot.handlers.buttonHandlers.ButtonHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ButtonContext {
    private Map<Buttons, ButtonHandler> buttonHandlers = new ConcurrentHashMap<>();

    public ButtonContext(List<ButtonHandler> buttonHandlers) {
        buttonHandlers.forEach(handler->this.buttonHandlers.put(handler.getHandlerName(), handler));
    }


    public SendMessage processButton(Buttons button, CallbackQuery callbackQuery) {
        ButtonHandler currentHandler = findButtonHandler(button);
        return currentHandler.handle(callbackQuery);
    }

    private ButtonHandler findButtonHandler(Buttons button) {
        return buttonHandlers.get(button);
    }
}
