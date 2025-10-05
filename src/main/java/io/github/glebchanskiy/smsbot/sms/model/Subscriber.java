package io.github.glebchanskiy.smsbot.sms.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
public class Subscriber {
    private String phoneNumber;
    private String username;
}
