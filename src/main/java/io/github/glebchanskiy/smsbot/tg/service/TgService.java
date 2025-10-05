package io.github.glebchanskiy.smsbot.tg.service;

import io.github.glebchanskiy.smsbot.domain.model.Message;

public interface TgService {
    void sendMessage(Message message);
}
