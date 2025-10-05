package io.github.glebchanskiy.smsbot.adb.service.impl;

import io.github.glebchanskiy.smsbot.adb.service.AdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.vidstige.jadb.JadbConnection;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class JadbService implements AdbService {
    private final JadbConnection jadbConnection;

//    adb shell service call \
//    isms 5 i32 0 s16 "com.android.mms.service" \
//    s16 "null" \
//    s16 "$1" \
//    s16 "null" \
//    s16 "'$2'" \
//    s16 "null" s16 "null" i32 0 i64 0
    @Override
    public void ismsSendCommand(String sendToNumber, String message) {
        var device = jadbConnection.getAnyDevice();

        try (var output = device.execute("shell", "service", "call",
                "isms", "5", "i32", "0", "s16", "com.android.mms.service",
                "s16", "null",
                "s16", sendToNumber,
                "s16", "null",
                "s16", message,
                "s16", "null", "s16", "null", "i32", "0", "i64", "0"
        )) {
            log.info("execute: ismsSendCommand {} {}", sendToNumber, message);
        } catch (Exception e) {
            log.error("Failed to execute: ismsSendCommand {} {}. Error: {}", sendToNumber, message, e.getMessage(), e);
        }
    }

    @Override
    public String ismsFetchCommand(Set<String> fromPhoneNumbers) {
        return "";
    }
}
