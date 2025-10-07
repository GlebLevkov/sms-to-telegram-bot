package io.github.glebchanskiy.smsbot.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class Message {
    private MessageType type;
    private String content;
    private Long from;
    private Long to;
    private String timestamp;
    private String toPhoneNumber;
    private String fromPhoneNumber;
}
