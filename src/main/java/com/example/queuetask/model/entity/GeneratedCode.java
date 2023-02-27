package com.example.queuetask.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(indexes = {
        @Index(columnList = "last_code", unique = true),
        @Index(columnList = "previous_code", unique = true)
})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class GeneratedCode extends BaseEntity {
    @Column(name = "last_code")
    private String lastCode;
    @Column(name = "previous_code")
    private String previousCode;
}
