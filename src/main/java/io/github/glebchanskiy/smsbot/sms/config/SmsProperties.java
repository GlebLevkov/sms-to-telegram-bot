package io.github.glebchanskiy.smsbot.sms.config;

import io.github.glebchanskiy.smsbot.sms.model.Subscriber;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Component
@RequiredArgsConstructor
@Accessors(fluent = true)
public class SmsProperties {
    @Value("${bot.subscribers}")
    private final List<String> rowSubscribers;
    private List<Subscriber> subscribers;
    private Set<String> phoneNumbers;

    @PostConstruct
    public void init() {
        this.subscribers = rowSubscribers.stream()
                .map(r -> r.split(":"))
                .map(arg -> new Subscriber().phoneNumber(arg[0]).username(arg[1]))
                .toList();
        this.phoneNumbers = subscribers.stream().map(Subscriber::phoneNumber).collect(Collectors.toSet());
    }
}
