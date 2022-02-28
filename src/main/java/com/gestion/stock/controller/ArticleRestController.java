package com.gestion.stock.controller;

import com.gestion.stock.controller.api.ArticleApi;
import com.gestion.stock.dto.ArticleDto;
import com.gestion.stock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleRestController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return this.articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return this.articleService.findById(id);
    }

    @Override
    public ArticleDto findByCode(String code) {
        return this.articleService.findByCode(code);
    }

    @Override
    public List<ArticleDto> findAll() {
        return this.articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        this.articleService.delete(id);
    }
}
