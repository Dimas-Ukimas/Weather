package com.dimasukimas.dto.response;

import java.util.UUID;

public record UserInfoDto(String login,
                          UUID sessionId
) {
}
