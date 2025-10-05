package io.github.glebchanskiy.smsbot.sms.service.impl;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.model.MessageType;
import io.github.glebchanskiy.smsbot.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class MockSmsServiceImpl implements SmsService {
    private final Long testTopic = -1003175355664L;
    @Override
    public List<Message> readMessages() {
        return List.of(new Message()
                .type(MessageType.SMS)
                .content("This is a sample message")
                .to(testTopic)
                .fromPhoneNumber("+375445925888")
        );
    }

    @Override
    public void sendMessage(Message message) {
        log.info("Sending SMS message: {}", message);
    }
}
