package ru.dbtc.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    public static final String TOKEN = "1809944157:AAEN5NXfJ5UanwZtwYqUNqXFOFKImkUWolE";
    public static final String USERNAME = "JavaEventManagerBot";

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            execute(message);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("KAK DELA?");
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            execute(sendMessage); // Call method to send the message
        }
    }
}
