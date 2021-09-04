package ru.dbtc.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@PropertySource("classpath:app.properties")
@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.botToken}")
    private String TOKEN = "1809944157:AAEN5NXfJ5UanwZtwYqUNqXFOFKImkUWolE";

    @Value("${bot.userName}")
    private String USERNAME = "JavaEventManagerBot";

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
            sendMessage.setText("wrrrr?");
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            execute(sendMessage); // Call method to send the message
        }
    }
}
