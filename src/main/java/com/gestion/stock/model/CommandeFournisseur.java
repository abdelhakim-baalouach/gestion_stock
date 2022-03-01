package com.gestion.stock.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Commandes_fournisseur")
@SQLDelete(sql = "UPDATE Commandes_fournisseur SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommandeFournisseur extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "date_commande")
    private OffsetDateTime dateCommande;

    @Column(name = "entreprise_id")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur", fetch = FetchType.LAZY)
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;
}
