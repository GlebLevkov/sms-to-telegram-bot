package io.github.glebchanskiy.smsbot.tg.service.impl;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.model.MessageType;
import io.github.glebchanskiy.smsbot.tg.service.TgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class TgMessageImpl implements TgService {
    private final TelegramClient telegramClient;
    @Override
    public void sendMessage(Message message) {
        try {
            var sendMessage = SendMessage.builder()
                    .chatId(message.to())
                    .text(message.content())
                    .build();
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }
}
