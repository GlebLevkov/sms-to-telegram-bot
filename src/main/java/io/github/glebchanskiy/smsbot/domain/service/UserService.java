package io.github.glebchanskiy.smsbot.domain.service;

import io.github.glebchanskiy.smsbot.domain.model.User;

import java.util.Map;

public interface UserService {
    Map<Long, User> getUserMap();
    void save(User user);
}
