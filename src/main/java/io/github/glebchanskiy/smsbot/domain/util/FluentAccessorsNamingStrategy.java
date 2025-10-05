package io.github.glebchanskiy.smsbot.domain.util;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

@Slf4j
@Component
@Primary
public class FluentAccessorsNamingStrategy extends DefaultAccessorNamingStrategy {

    @Override
    public boolean isGetterMethod(ExecutableElement method) {
        // no-arg, non-void -> treat as getter
        return method.getParameters().isEmpty()
                && method.getReturnType().getKind() != TypeKind.VOID;
    }

    @Override
    public boolean isSetterMethod(ExecutableElement method) {
        // single-arg void OR fluent setter returning this
        return method.getParameters().size() == 1;
    }

    @Override
    public String getPropertyName(ExecutableElement method) {
        return method.getSimpleName().toString();
    }
}