package com.gestion.stock.model;

import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fournissuers")
@SQLDelete(sql = "UPDATE fournissuers SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fournissuer extends AbstractEntity {

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Enumerated
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
}
