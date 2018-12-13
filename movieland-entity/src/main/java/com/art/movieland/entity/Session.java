package com.art.movieland.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class Session {
    private final UserToken userToken;
    private final LocalDateTime expirationTime;
}
