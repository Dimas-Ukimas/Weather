package com.dimasukimas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Sessions")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private LocalDateTime expiresAt;

}
