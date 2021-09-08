package ru.dbtc.bot.handlers.buttonHandlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.dbtc.bot.constants.Buttons;

public interface ButtonHandler {
    SendMessage handle(CallbackQuery buttonQuery);
    Buttons getHandlerName();
}
