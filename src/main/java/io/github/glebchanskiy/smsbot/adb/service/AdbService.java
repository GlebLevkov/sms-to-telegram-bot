package io.github.glebchanskiy.smsbot.adb.service;


import java.util.Set;

public interface AdbService {
    void ismsSendCommand(String sendToNumber, String message);
    String ismsFetchCommand(Set<String> fromPhoneNumbers);
}
