package io.github.glebchanskiy.smsbot.tg.mapper;

import io.github.glebchanskiy.smsbot.domain.model.Message;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.telegram.telegrambots.meta.api.objects.Update;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface UpdateToMessageMapper {

    @Mapping(target = "content", source = "update.message.text")
    @Mapping(target = "from", source = "update.message.from.id")
    @Mapping(target = "to", source = "update.message.chatId")
    @Mapping(target = "timestamp", source = "update.message.date")
    default Message toMessage(Update update) {
        return new Message()
                .content(update.getMessage().getText())
                .from(update.getMessage().getFrom().getId())
                .to(update.getMessage().getChatId())
                .timestamp(update.getMessage().getDate().toString());
    }
}
