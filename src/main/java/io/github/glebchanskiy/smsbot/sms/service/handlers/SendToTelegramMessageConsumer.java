package io.github.glebchanskiy.smsbot.sms.service.handlers;

import io.github.glebchanskiy.smsbot.domain.model.MessageType;
import io.github.glebchanskiy.smsbot.domain.service.UserService;
import io.github.glebchanskiy.smsbot.domain.util.Formatter;
import io.github.glebchanskiy.smsbot.sms.service.SmsConsumer;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.tg.service.TgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
public class SendToTelegramMessageConsumer implements SmsConsumer {
    private final TgService tgService;
    private final UserService userService;

    @Override
    public void run(Message message) {
        log.info("Send message to telegram");
        tgService.sendMessage(message.content(generateText(message)));
    }

    private String generateText(Message message) {
        if (message.type().equals(MessageType.SMS)) {
            return Formatter.format(message, userService.getUserMap(), false);
        }
        throw new RuntimeException("Message type not supported");
    }
}
