package ru.dbtc.bot.services;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class KeyboardMarkUpServices {
    private InlineKeyboardButton getButton(String text, String callbackValue) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        return button.setCallbackData(callbackValue);
    }
    public InlineKeyboardMarkup getInlineKeyboardMarkupInRange(int from, int to) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            keyboardButtonsRow.add(getButton("", i + ""));
            if (i % 6 == 0) {
                buttons.add(keyboardButtonsRow);
                keyboardButtonsRow = new ArrayList<>();
            }
        }
        buttons.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getReplyKeyboardMarkup(Map<String, String> names) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        for (String key : names.keySet()) {
            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton(key));
            keyboard.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup(Map<String, String> names) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (String key:names.keySet()) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(getButton(key, names.get(key)));
            buttons.add(keyboardButtonsRow);
        }
        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }
}
