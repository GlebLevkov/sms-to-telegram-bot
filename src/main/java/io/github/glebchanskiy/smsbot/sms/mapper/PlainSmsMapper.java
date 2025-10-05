package io.github.glebchanskiy.smsbot.sms.mapper;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlainSmsMapper {

    default List<Message> toMessages(String plain) {
        var topic = -1003175355664L;
        return Arrays.stream(plain.split("\n"))
                .map(row -> new Message().content(row).to(topic)).toList();
    }
}
