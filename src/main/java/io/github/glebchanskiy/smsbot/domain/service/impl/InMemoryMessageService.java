package io.github.glebchanskiy.smsbot.domain.service.impl;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import io.github.glebchanskiy.smsbot.domain.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryMessageService implements MessageService {
    private final Map<Long, List<Message>> store = new ConcurrentHashMap<>();

    @Override
    public void addMessageToQueue(Message message) {
        var topic = store.get(message.to());

        if (Objects.nonNull(topic)) {
            topic.add(message);
        } else {
            store.put(message.to(), createBucketWith(message));
        }
    }

    @Override
    public List<Message> readMessagesQueue(Long topic, String who) {
        return store.remove(topic);
    }

    private List<Message> createBucketWith(Message message) {
        return new LinkedList<>(List.of(message));
    }
}
