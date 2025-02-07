package org.mohammed.cmsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.mohammed.cmsapi.model.embeddable.QuestionnaireCheck;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Set;


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

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "muscle_balance_id")
    private MuscleBalance muscleBalance;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FitnessTest> fitnessTests;

    @ElementCollection
    @CollectionTable(
            name = "trainee_questionnaire_checks",
            joinColumns = @JoinColumn(name = "trainee_id")
    )
    private Set<QuestionnaireCheck> questionnaireChecks;

    public void addQuestionnaireCheck(String checkName) {
        QuestionnaireCheck questionnaireCheck = new QuestionnaireCheck();
        questionnaireCheck.setCheckName(checkName);
        questionnaireCheck.setIsChecked(true);
        this.questionnaireChecks.add(questionnaireCheck);
    }

}