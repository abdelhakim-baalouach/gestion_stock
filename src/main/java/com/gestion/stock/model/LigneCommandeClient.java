package com.gestion.stock.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignes_commande_client")
@SQLDelete(sql = "UPDATE lignes_commande_client SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LigneCommandeClient extends AbstractEntity {

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prix_unitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "entreprise_id")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "commande_client_id")
    private CommandeClient commandeClient;
}
