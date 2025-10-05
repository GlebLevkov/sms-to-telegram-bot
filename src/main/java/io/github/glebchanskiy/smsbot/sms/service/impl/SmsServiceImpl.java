package io.github.glebchanskiy.smsbot.sms.service.impl;

import io.github.glebchanskiy.smsbot.adb.service.AdbService;
import io.github.glebchanskiy.smsbot.sms.config.SmsProperties;
import io.github.glebchanskiy.smsbot.sms.mapper.PlainSmsMapper;
import io.github.glebchanskiy.smsbot.sms.service.SmsService;
import io.github.glebchanskiy.smsbot.domain.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final AdbService adbService;
    private final SmsProperties smsProperties;
    private final PlainSmsMapper plainSmsMapper;

    @Override
    public List<Message> readMessages() {
        var messages = plainSmsMapper.toMessages(adbService.ismsFetchCommand(smsProperties.phoneNumbers()));
        log.info("read sms messages. Count: {}", messages.size());
        return messages;
    }

    @Override
    public void sendMessage(Message message) {
        log.info("send sms message to {} [{}]", message.toPhoneNumber(), message.content());
        adbService.ismsSendCommand(message.toPhoneNumber(), message.content());
    }
}
