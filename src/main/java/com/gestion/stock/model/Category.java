package com.gestion.stock.model;

import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categorys")
@SQLDelete(sql = "UPDATE categorys SET state = 'DELETED' WHERE id=?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
