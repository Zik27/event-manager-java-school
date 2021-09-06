package ru.dbtc.bot;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Setter
@Slf4j
public class Bot extends TelegramWebhookBot {
    @Value("${bot.botToken}")
    private String botToken;//= "1809944157:AAEN5NXfJ5UanwZtwYqUNqXFOFKImkUWolE";
    @Value("${bot.userName}")
    private String botUserName;// = "JavaEventManagerBot";
    @Value("${bot.webHookPath}")
    private String webHookPath;

    private TelegramFacade telegramFacade;

    public Bot(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        BotApiMethod<?> replyToUser = telegramFacade.handleUpdate(update);
        return replyToUser;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
