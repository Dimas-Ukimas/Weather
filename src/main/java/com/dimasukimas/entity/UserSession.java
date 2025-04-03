package com.dimasukimas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Sessions")
@Builder
@Getter
public class UserSession {

    @Id
    private UUID id;

    @OneToOne
    @NotNull
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @NotNull
    private LocalDateTime expiresAt;

}
