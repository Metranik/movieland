package com.art.movieland.entity;

import lombok.*;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class UserToken {
    private final String uuid;
    private final String name;
}
