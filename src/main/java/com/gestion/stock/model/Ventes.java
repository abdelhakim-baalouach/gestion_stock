package com.gestion.stock.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ventes")
@SQLDelete(sql = "UPDATE ventes SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ventes extends AbstractEntity {

    @Column(name = "cod")
    private String code;

    @Column(name = "date_vente")
    private OffsetDateTime dateVente;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "entreprise_id")
    private Integer idEntreprise;
}
