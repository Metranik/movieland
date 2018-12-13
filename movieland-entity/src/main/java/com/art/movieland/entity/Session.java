package com.art.movieland.entity;

import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class Session {
    private final UserToken userToken;
    private final LocalDateTime expirationTime;
    private final User user;
}
