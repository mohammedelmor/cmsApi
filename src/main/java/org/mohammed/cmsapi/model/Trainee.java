package org.mohammed.cmsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainee_seq")
    @SequenceGenerator(name = "trainee_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Min(5)
    private Integer age;

}