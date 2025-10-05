package io.github.glebchanskiy.smsbot.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class User {
    private Long id;
    private String username;
    private String name;
}
