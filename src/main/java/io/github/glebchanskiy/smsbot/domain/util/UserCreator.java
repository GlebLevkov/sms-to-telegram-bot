package io.github.glebchanskiy.smsbot.domain.util;

import io.github.glebchanskiy.smsbot.domain.model.User;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;

@UtilityClass
public class UserCreator {
    public User of(Update message) {
        var user = message.getMessage().getFrom();
        var username = user.getUserName();
        var firstName = user.getFirstName();
        var lastName = user.getLastName();
        var id = user.getId();
        return new User().id(id).username(username).name("%s %s".formatted(firstName, lastName));
    }

    public User of(String phoneNumber, String username) {
        var id = Long.parseLong(phoneNumber.replace("+", ""));
        return new User().id(id).username(username).name(username);
    }
}
