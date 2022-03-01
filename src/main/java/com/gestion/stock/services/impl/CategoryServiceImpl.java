package com.gestion.stock.services.impl;

import com.gestion.stock.dto.CategoryDto;
import com.gestion.stock.exception.EntityNotFoundException;
import com.gestion.stock.exception.InvalidEntityException;
import com.gestion.stock.model.Category;
import com.gestion.stock.repository.CategoryRepository;
import com.gestion.stock.services.CategoryService;
import com.gestion.stock.utils.ErrorCodes;
import com.gestion.stock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        this.checkCategoryDtoIsValid(dto);
        var category = this.categoryRepository.save(this.mapToCategory(dto)) ;
        return this.mapToCategoryDto(category);
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (Objects.isNull(id)) {
            log.error("ID categorie est null");
            return null;
        }
        return  this.categoryRepository
                .findByIdAndState_Active(id)
                .map(this::mapToCategoryDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun categorie avec l'ID = " + id + " n'ete trouve dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (Objects.isNull(code)) {
            log.error("Code categorie est null");
            return null;
        }
        return this.categoryRepository
                .findCategoryByCodeAndState_Active(code)
                .map(this::mapToCategoryDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Aucun categorie avec le CODE = " + code + " n'ete trouve dans la BDD", ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> findAll() {
        return this.categoryRepository
                .findAll()
                .stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        var articleDto = this.findById(id);
        this.categoryRepository.delete(this.mapToCategory(articleDto));
    }

    private void checkCategoryDtoIsValid(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Categorie n'est pas valide {}", dto);
            throw new InvalidEntityException("Categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
    }

    private Category mapToCategory(CategoryDto dto) {
        return CategoryDto.builder()
                .build()
                .toEntity(dto);
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .build()
                .fromEntity(category);
    }
}
