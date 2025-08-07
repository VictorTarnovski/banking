package com.victor_tarnovski.banking.infra.box_messages;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxMessageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
    @Column(name = "type")
    public String type;
    @Column(name = "payload")
    public String payload;
    @Column(name = "processed_at")
    public OffsetDateTime processedAt;
}
