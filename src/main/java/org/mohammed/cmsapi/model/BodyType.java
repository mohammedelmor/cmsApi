package org.mohammed.cmsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.mohammed.cmsapi.listener.BodyTypeListener;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Entity
@EntityListeners({AuditingEntityListener.class, BodyTypeListener.class})
public class BodyType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_type_seq")
    @SequenceGenerator(name = "body_type_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String image;
    private String fullPath;

    @Transient
    private MultipartFile file;
}
