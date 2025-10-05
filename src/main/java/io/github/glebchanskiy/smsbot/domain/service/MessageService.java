package io.github.glebchanskiy.smsbot.domain.service;

import io.github.glebchanskiy.smsbot.domain.model.Message;

import java.util.List;

public interface MessageService {
    void addMessageToQueue(Message message);
    List<Message> readMessagesQueue(Long topic, String who);
}
