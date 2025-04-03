package com.dimasukimas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@Table(name = "Locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @NotNull
    @Column(nullable = false)
    BigDecimal latitude;

    @NotNull
    @Column(nullable = false)
    BigDecimal longitude;

}
