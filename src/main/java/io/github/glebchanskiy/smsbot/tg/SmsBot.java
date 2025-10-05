package io.github.glebchanskiy.smsbot.tg;

import io.github.glebchanskiy.smsbot.tg.consumer.TelegramMessageConsumer;
import io.github.glebchanskiy.smsbot.tg.mapper.UpdateToMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsBot implements LongPollingSingleThreadUpdateConsumer {
    private final UpdateToMessageMapper messageMapper;
    private final List<TelegramMessageConsumer> consumers;

    @Override
    public void consume(Update update) {
        log.info("Processing update {}", update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = messageMapper.toMessage(update);

            consumers.stream()
                    .filter(c -> c.matches(message))
                    .forEach(c -> {
                        c.run(message);
                        c.runExtended(update);
                    });
        }
    }
}
