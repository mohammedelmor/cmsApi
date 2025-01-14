package org.mohammed.cmsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MuscleBalance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_type_seq")
    @SequenceGenerator(name = "body_type_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
