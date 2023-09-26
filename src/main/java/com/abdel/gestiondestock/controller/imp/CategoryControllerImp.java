package com.abdel.gestiondestock.controller.imp;

import com.abdel.gestiondestock.DTO.CategoryDto;
import com.abdel.gestiondestock.controller.CategoryController;
import com.abdel.gestiondestock.repository.CategoryRepository;
import com.abdel.gestiondestock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryControllerImp implements CategoryController {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryControllerImp(
            CategoryService categoryService,
            CategoryRepository categoryRepository

    ) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto){
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }



    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {
        return categoryService.findByCodeCategory(codeCategory);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);

    }
}
