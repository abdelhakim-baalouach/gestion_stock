package com.gestion.stock.controller.api;

import com.gestion.stock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gestion.stock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/article", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Enregistrer un article (Ajouter / Modifier)",
            notes = "Cette methode permet d'enregistrer ou modifier un article",
            response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'object article creer / modifie"),
            @ApiResponse(code = 400, message = "L'object article n'est pas valide"),
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Rechercher un article par ID",
            notes = "Cette methode permet de chercher un article par son ID",
            response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/article/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Rechercher un article par code",
            notes = "Cette methode permet de chercher un article par son ID",
            response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le code fourni")
    })
    ArticleDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Renvoi la liste des article",
            notes = "Cette methode permet de chercher et renvoyer la liste des articles qui existent dans la BDD",
            responseContainer = "List<ArticleDto>"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des article / une liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/article/{id}")
    @ApiOperation(
            value = "Supprimer un article par ID",
            notes = "Cette methode permet de supprimer un article par ID"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete supprime"),
    })
    void delete(@PathVariable Integer id);
}
