package ru.dbtc.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.dbtc.bot.constants.BotState;

public interface InputMessageHandler {
    SendMessage handle(Message message);
    //todo убрать второй метод
    BotState getHandlerName();
}
