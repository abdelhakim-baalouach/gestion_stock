package com.gestion.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestion.stock.util.StateEnum;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private OffsetDateTime lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    protected StateEnum state;

    protected AbstractEntity() {
        this.state = StateEnum.ACTIVE;
    }

}
