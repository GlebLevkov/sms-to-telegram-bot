package io.github.glebchanskiy.smsbot.sms.sheduler;

import io.github.glebchanskiy.smsbot.sms.service.SmsConsumer;
import io.github.glebchanskiy.smsbot.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadSmsScheduler {
    private final SmsService smsService;
    private final List<SmsConsumer> consumers;

    @Scheduled(fixedRate = 10000)
    public void readMessages() {
        var messages = smsService.readMessages();

        messages.forEach(message -> consumers.stream()
                .filter(c -> c.matches(message))
                .forEach(c -> c.run(message)));
    }

    @Scheduled(fixedRate = 22000)
    public void readCommand_test() {
        var messages = smsService.readMessages();
        messages.forEach(message -> {
            message.content("/h");
        });

        messages.forEach(message -> consumers.stream()
                .filter(c -> c.matches(message))
                .forEach(c -> c.run(message)));
    }
}
