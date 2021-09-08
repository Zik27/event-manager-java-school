package ru.dbtc.bot.handlers.messageHandlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.dbtc.bot.cache.UserDataCache;
import ru.dbtc.bot.constants.BotState;
import ru.dbtc.bot.services.KeyboardMarkUpServices;
import ru.dbtc.bot.services.ReplyMessageService;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class MainMenuHandler implements InputMessageHandler{
    private UserDataCache userDataCache;
    private ReplyMessageService replyMessageService;
    private KeyboardMarkUpServices keyboardMarkUpServices;

    @Override
    public SendMessage handle(Message message) {
        //todo внести данные в бд User_service
        Long chatId = message.getChatId();
        Map<String, String> names = new HashMap<>();
        names.put("Найти новое мероприятие", "buttonNewEvent");
        names.put("Мои мероприятия", "buttonMyEvent");
        names.put("Изменить информацию о себе", "buttonChangeInfo");
        names.put("Помощь", "buttonHelp");
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardMarkUpServices.getReplyKeyboardMarkup(names);
        SendMessage replyToUser = createMessageWithKeyboard(chatId, "Воспользуйтесь главным меню", replyKeyboardMarkup);
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }

    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}
