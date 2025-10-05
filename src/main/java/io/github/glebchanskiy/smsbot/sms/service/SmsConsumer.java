package io.github.glebchanskiy.smsbot.sms.service;

import io.github.glebchanskiy.smsbot.domain.model.Message;

public interface SmsConsumer {
    default boolean matches(Message message) {
        return true;
    }
    void run(Message message);
}
