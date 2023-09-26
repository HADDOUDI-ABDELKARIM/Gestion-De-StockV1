package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.CategoryDto;
import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.Exception.InvalidEntityException;
import com.abdel.gestiondestock.model.Category;
import com.abdel.gestiondestock.repository.CategoryRepository;
import com.abdel.gestiondestock.service.CategoryService;
import com.abdel.gestiondestock.validator.CategoryValidator;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors= CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Category is not valid",dto);
            throw new InvalidEntityException("Category is not valid,", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }
        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(dto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id==null){
            log.error("id :{} is null",id);
        }
        Optional<Category> category=categoryRepository.findById(id);
        CategoryDto dto=CategoryDto.fromEntity(category.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException("Aucun Category avec id = "+id+"n'ete trouver dans la BDA"
                        ,ErrorCodes.CATEGORY_NOT_VALID));
    }

    @Override
    public CategoryDto findByCodeCategory(String code) {
        if (code==null){
            log.error("code is null");
        }
        Optional<Category> category=categoryRepository.findByCode(code);
        CategoryDto dto=CategoryDto.fromEntity(category.get());
        return Optional.of(dto).orElseThrow(()->new EntityNotFoundException("Aucun Category avec code = "+code+"n'ete trouver dans la BDA"
                ,ErrorCodes.CATEGORY_NOT_FOUND));    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            return;
        }
        categoryRepository.deleteById(id);

    }
}
