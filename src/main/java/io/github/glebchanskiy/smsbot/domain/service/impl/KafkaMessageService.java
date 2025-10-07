package io.github.glebchanskiy.smsbot.domain.service.impl;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class KafkaMessageService implements MessageService {
    private final KafkaService kafkaService;


    @Override
    public void addMessageToQueue(Message message) {
        kafkaService.sendMessage(message);
    }

    @Override
    public List<Message> readMessagesQueue(Long topic, String who) {
        return kafkaService.readMessages(who);
    }

}
