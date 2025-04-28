package com.dimasukimas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Sessions")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSession {

    @Id
    private UUID id;

    @OneToOne
    @NotNull @JoinColumn(name = "userId", nullable = false)
    private User user;

    @NotNull @Column(nullable = false)
    private LocalDateTime expiresAt;

}
