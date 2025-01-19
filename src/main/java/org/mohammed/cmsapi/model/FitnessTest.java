package org.mohammed.cmsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FitnessTest extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fitness_test_seq")
    @SequenceGenerator(name = "fitness_test_seq")
    @Column(nullable = false)
    private Long id;

    private String exercise;

    private Integer repetition;

    private Integer duration;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trainee trainee;


}
