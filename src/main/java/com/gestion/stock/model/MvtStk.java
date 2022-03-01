package com.gestion.stock.model;

import com.gestion.stock.utils.TypeMvtStk;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvt_stk")
@SQLDelete(sql = "UPDATE mvt_stk SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MvtStk extends AbstractEntity {

    @Column(name = "date_mvt")
    private OffsetDateTime dateMvt;

    @Column(name = "quantite")
    private OffsetDateTime quantite;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_mvt")
    private TypeMvtStk typeMvt;

    @Column(name = "entreprise_id")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
