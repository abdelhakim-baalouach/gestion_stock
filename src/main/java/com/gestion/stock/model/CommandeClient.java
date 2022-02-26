package com.gestion.stock.model;

import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandes_client")
@SQLDelete(sql = "UPDATE commandes_client SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommandeClient extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "date_commande")
    private OffsetDateTime dateCommande;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;
}
