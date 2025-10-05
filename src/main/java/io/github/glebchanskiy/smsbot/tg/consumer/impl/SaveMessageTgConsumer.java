package io.github.glebchanskiy.smsbot.tg.consumer.impl;

import io.github.glebchanskiy.smsbot.tg.consumer.TelegramMessageConsumer;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveMessageTgConsumer implements TelegramMessageConsumer {
    private final MessageService messageService;

    @Override
    public void run(Message message) {
        messageService.addMessageToQueue(message);
    }
}
