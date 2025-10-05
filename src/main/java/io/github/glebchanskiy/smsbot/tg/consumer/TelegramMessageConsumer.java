package io.github.glebchanskiy.smsbot.tg.consumer;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramMessageConsumer {
    default boolean matches(Message message) {
        return true;
    }
    default void run(Message message) {}
    default void runExtended(Update update) {}
}
