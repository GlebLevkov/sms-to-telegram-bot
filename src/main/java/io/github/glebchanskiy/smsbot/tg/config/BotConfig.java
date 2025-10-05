package io.github.glebchanskiy.smsbot.tg.config;

import io.github.glebchanskiy.smsbot.tg.SmsBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class BotConfig {

    @Value("${bot.token}")
    private String token;

    @Bean(destroyMethod = "close")
    @SuppressWarnings("java:S2095")
    TelegramBotsLongPollingApplication botBean(SmsBot smsBot) throws TelegramApiException {
        var bot = new TelegramBotsLongPollingApplication();
        try {
            bot.registerBot(token, smsBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return bot;
    }

    @Bean
    TelegramClient telegramClient() {
        return new OkHttpTelegramClient(token);
    }
}
