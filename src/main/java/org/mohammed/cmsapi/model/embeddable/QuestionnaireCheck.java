package org.mohammed.cmsapi.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class QuestionnaireCheck {
    private String checkName;
    private Boolean isChecked;
}
