package io.github.glebchanskiy.smsbot.tg.consumer.impl;

import io.github.glebchanskiy.smsbot.tg.consumer.TelegramMessageConsumer;
import io.github.glebchanskiy.smsbot.domain.service.UserService;
import io.github.glebchanskiy.smsbot.domain.util.UserCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserTgConsumer implements TelegramMessageConsumer {

    private final UserService userService;

    @Override
    public void runExtended(Update update) {
        userService.save(UserCreator.of(update));
    }
}
