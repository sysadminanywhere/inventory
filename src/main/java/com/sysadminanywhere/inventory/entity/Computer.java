package com.sysadminanywhere.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String dns;

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Installation> installations;

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ComputerHardware> computerHardwares;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}