package com.dimasukimas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "Locations",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "latitude", "longitude"})
        }
)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull @Column(nullable = false)
    private String name;

    @ManyToOne
    @NotNull @JoinColumn(name = "userId", nullable = false)
    private User user;

    @NotNull @Column(nullable = false)
    BigDecimal latitude;

    @NotNull @Column(nullable = false)
    BigDecimal longitude;

}
