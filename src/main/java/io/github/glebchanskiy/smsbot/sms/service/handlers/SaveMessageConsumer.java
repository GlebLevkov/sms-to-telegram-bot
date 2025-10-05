package io.github.glebchanskiy.smsbot.sms.service.handlers;

import io.github.glebchanskiy.smsbot.sms.service.SmsConsumer;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMessageConsumer implements SmsConsumer {
    private final MessageService messageService;

    @Override
    public void run(Message message) {
        messageService.addMessageToQueue(message);
    }
}
