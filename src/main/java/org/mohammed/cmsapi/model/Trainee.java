package org.mohammed.cmsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Trainee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainee_seq")
    @SequenceGenerator(name = "trainee_seq")
    @Column(nullable = false)
    private Long id;

    private String name;

    private Integer age;

    private String phoneNumber;
}