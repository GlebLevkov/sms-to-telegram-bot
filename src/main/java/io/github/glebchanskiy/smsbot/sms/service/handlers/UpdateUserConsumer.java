package io.github.glebchanskiy.smsbot.sms.service.handlers;

import io.github.glebchanskiy.smsbot.sms.config.SmsProperties;
import io.github.glebchanskiy.smsbot.sms.model.Subscriber;
import io.github.glebchanskiy.smsbot.sms.service.SmsConsumer;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.UserService;
import io.github.glebchanskiy.smsbot.domain.util.UserCreator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Order(-1)
@Component
@RequiredArgsConstructor
public class UpdateUserConsumer implements SmsConsumer {
    private final SmsProperties smsProperties;
    private final UserService userService;

    private Map<String, String> phoneToUsernameMap;

    @PostConstruct
    public void init() {
        this.phoneToUsernameMap = smsProperties.subscribers().stream()
                .collect(Collectors.toMap(Subscriber::phoneNumber, Subscriber::username));
    }

    @Override
    public void run(Message message) {
        log.info("Updating subscriber user");
        String phoneNumber = message.fromPhoneNumber();
        String username = phoneToUsernameMap.getOrDefault(phoneNumber, phoneNumber);

        var user = UserCreator.of(phoneNumber, username);
        userService.save(user);
    }
}
