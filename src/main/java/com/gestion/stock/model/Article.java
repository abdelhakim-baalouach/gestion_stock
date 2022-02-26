package com.gestion.stock.model;

import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "articles")
@SQLDelete(sql = "UPDATE articles SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article extends AbstractEntity {

    @Column(name = "cod")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix_unitaire_Ht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "taux_tva")
    private BigDecimal tauxTva;

    @Column(name = "prix_unitaire_ttc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
