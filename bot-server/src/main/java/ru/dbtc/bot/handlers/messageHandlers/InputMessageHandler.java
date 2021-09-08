package ru.dbtc.bot.handlers.messageHandlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dbtc.bot.constants.BotState;

public interface InputMessageHandler {
    SendMessage handle(Message message);
    BotState getHandlerName();
}
