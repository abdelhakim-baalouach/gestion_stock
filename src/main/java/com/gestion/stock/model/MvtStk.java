package com.gestion.stock.model;

import com.gestion.stock.util.TypeMvtStk;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvt_stk")
@SQLDelete(sql = "UPDATE mvt_stk SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@Builder
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

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
