package io.github.glebchanskiy.smsbot.domain.service.impl;

import com.google.gson.Gson;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {
    private static final String TOPIC = "sms";
    private final Gson gson;
    private final KafkaConsumerConfigProps config;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Map<String, KafkaConsumer<String, String>> consumerMap = new ConcurrentHashMap<>();

    public void sendMessage(Message message) {
        try {
            kafkaTemplate.send(TOPIC, gson.toJson(message)).get();
        } catch (Exception e) {
            log.error("Error while sending message", e);
            Thread.currentThread().interrupt();
        }
    }

    public List<Message> readMessages(String who) {
        var consumer = getOrCreateConsumer(who);
        var records = consumer.poll(Duration.ofSeconds(2));

        return StreamSupport.stream(records.spliterator(), false)
                .map(ConsumerRecord::value)
                .map(this::toMessage)
                .toList();
    }

    private Message toMessage(String content) {
        return gson.fromJson(content, Message.class);
    }

    private KafkaConsumer<String, String> getOrCreateConsumer(String who) {
        if (!consumerMap.containsKey(who)) {
            log.info("Creating new consumer for: {}", who);
            var consumer = new KafkaConsumer<String, String>(getProperties(who));
            consumer.subscribe(List.of(TOPIC));
            consumerMap.put(who, consumer);
            return consumer;
        } else {
            log.info("Getting already exists consumer for: {}", who);
            return consumerMap.get(who);
        }
    }

    private Properties getProperties(String who) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, who);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }
}
