package io.github.glebchanskiy.smsbot.domain.service.impl;

import io.github.glebchanskiy.smsbot.domain.model.User;
import io.github.glebchanskiy.smsbot.domain.service.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserService implements UserService {
    private final Map<Long, User> store = new ConcurrentHashMap<>();

    @Override
    public Map<Long, User> getUserMap() {
        return new HashMap<>(store);
    }

    @Override
    public void save(User user) {
        store.put(user.id(), user);
    }
}
