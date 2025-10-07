package io.github.glebchanskiy.smsbot.sms.service.handlers;

import io.github.glebchanskiy.smsbot.sms.service.SmsConsumer;
import io.github.glebchanskiy.smsbot.sms.service.SmsService;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.MessageService;
import io.github.glebchanskiy.smsbot.domain.service.UserService;
import io.github.glebchanskiy.smsbot.domain.util.Formatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Order(3)
@Component
@RequiredArgsConstructor
public class HistoryCommandConsumer implements SmsConsumer {
    private static final Set<String> HISTORY_COMMANDS = Set.of("/history", "/h");
    private final MessageService messageService;
    private final SmsService smsService;
    private final UserService userService;

    @Override
    public boolean matches(Message message) {
        return HISTORY_COMMANDS.stream().anyMatch(cmd -> message.content().toLowerCase().contains(cmd));
    }

    @Override
    public void run(Message message) {
        log.info("Received a history command");
        try {
            var historyMessages = messageService.readMessagesQueue(message.to(), message.fromPhoneNumber());
            var text = Formatter.format(historyMessages, userService.getUserMap());
            smsService.sendMessage(newMessage(text, message.fromPhoneNumber()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private Message newMessage(String text, String fromPhone) {
        return new Message().content(text).toPhoneNumber(fromPhone);
    }
}
