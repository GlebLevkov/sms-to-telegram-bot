package io.github.glebchanskiy.smsbot.sms.service;

import io.github.glebchanskiy.smsbot.domain.model.Message;

import java.util.List;

public interface SmsService {
    List<Message> readMessages();
    void sendMessage(Message message);
}
