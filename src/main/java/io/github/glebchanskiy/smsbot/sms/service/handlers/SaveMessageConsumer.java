package io.github.glebchanskiy.smsbot.sms.service.handlers;

import io.github.glebchanskiy.smsbot.sms.service.SmsConsumer;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class SaveMessageConsumer implements SmsConsumer {
    private final MessageService messageService;

    @Override
    public void run(Message message) {
        log.info("Message saved to kafka");
        messageService.addMessageToQueue(message);
    }
}
