package com.gestion.stock.dto;

import com.gestion.stock.model.MvtStk;
import com.gestion.stock.utils.StateEnum;
import com.gestion.stock.utils.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Builder
@Data
public class MvtStkDto {

    private Integer id;

    private StateEnum state;

    private OffsetDateTime dateMvt;

    private OffsetDateTime quantite;

    private TypeMvtStk typeMvt;

    private ArticleDto article;

    public MvtStkDto fromEntity(MvtStk mvtStk) {
        if (Objects.isNull(mvtStk)) {
            return null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .state(mvtStk.getState())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvt(mvtStk.getTypeMvt())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .fromEntity(mvtStk.getArticle())
                )
                .build();
    }

    public MvtStk toEntity(MvtStkDto mvtStkDto) {
        if (Objects.isNull(mvtStkDto)) {
            return null;
        }

        return MvtStk.builder()
                .id(mvtStkDto.getId())
                .state(mvtStkDto.getState())
                .dateMvt(mvtStkDto.getDateMvt())
                .quantite(mvtStkDto.getQuantite())
                .typeMvt(mvtStkDto.getTypeMvt())
                .article(
                        ArticleDto
                                .builder()
                                .build()
                                .toEntity(mvtStkDto.getArticle())
                )
                .build();
    }
}
