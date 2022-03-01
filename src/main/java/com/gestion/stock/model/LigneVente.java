package com.gestion.stock.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignes_Vente")
@SQLDelete(sql = "UPDATE lignes_Vente SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LigneVente extends AbstractEntity {

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prix_unitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "entreprise_id")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "ventes_id")
    private Ventes ventes;
}
