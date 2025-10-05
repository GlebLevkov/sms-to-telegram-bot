package io.github.glebchanskiy.smsbot.domain.util;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.model.User;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@UtilityClass
public class Formatter {
    public String format(Message message, Map<Long, User> userMap, boolean repeat) {
        var user = userMap.get(from(message));

        if (repeat) {
            return "> %s".formatted(message.content());
        } else {
            return Objects.isNull(user)
                    ? "%s: %s".formatted(from(message), message.content())
                    : "%s: %s".formatted(user.username(), message.content());
        }
    }

    private Long from(Message message) {
        return message.from() == null
                ? Long.parseLong(message.fromPhoneNumber().replace("+", ""))
                : message.from();
    }

    public String format(List<Message> messages, Map<Long, User> userMap) {
        if (Objects.isNull(messages) || messages.isEmpty()) {
            return "empty message";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (var i = 0; i < messages.size(); i++) {
            var prevMessage = i == 0 ? null : messages.get(i);
            var line = messages.get(i);
            var nextLine = i + 1 >= messages.size() ? null : messages.get(i + 1);

            var user = userMap.get(from(line));

            if (nextLine != null && from(line).equals(from(nextLine)) && (prevMessage == null || !from(line).equals(from(prevMessage)))) {
                stringBuilder.append("%s:".formatted(user.username())).append(System.lineSeparator());
                stringBuilder.append("> %s".formatted(line.content())).append(System.lineSeparator());
                continue;
            }

            if (prevMessage != null && from(line).equals(from(prevMessage))) {
                stringBuilder.append("> %s".formatted(line.content())).append(System.lineSeparator());
                continue;
            }

            log.info("user: {}, line: {}", user, line);

            stringBuilder.append("%s: %s".formatted(user.username(), line.content()));
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
