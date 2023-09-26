package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.DTO.CategoryDto;
import com.abdel.gestiondestock.service.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImpTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldSaveCategoryWithSuccess(){
       CategoryDto expectedCategory= CategoryDto.builder()
                .code("cat test")
                .designation("designation test")
                .idEntreprise(2)
                .build();
       CategoryDto saveCategory=categoryService.save(expectedCategory);
        Assertions.assertNotNull(saveCategory);
        Assertions.assertNotNull(saveCategory.getId());
        Assertions.assertEquals(expectedCategory.getCode(),saveCategory.getCode());
        Assertions.assertEquals(expectedCategory.getDesignation(),saveCategory.getDesignation());
        Assertions.assertEquals(expectedCategory.getIdEntreprise(),saveCategory.getIdEntreprise());
    }

    @Test
    public void shouldUpdateCategoryWithSuccess(){
        CategoryDto expectedCategory= CategoryDto.builder()
                .code("cat test")
                .designation("designation test")
                .idEntreprise(2)
                .build();
        CategoryDto saveCategory=categoryService.save(expectedCategory);
        CategoryDto updateCategory=saveCategory;
        updateCategory.setCode("cat update");
        System.out.println(updateCategory.getId());
        categoryService.save(updateCategory);
        Assertions.assertNotNull(updateCategory);
        Assertions.assertNotNull(updateCategory.getId());
        Assertions.assertEquals(updateCategory.getCode(),saveCategory.getCode());
        Assertions.assertEquals(updateCategory.getDesignation(),saveCategory.getDesignation());
        Assertions.assertEquals(updateCategory.getIdEntreprise(),saveCategory.getIdEntreprise());
    }

}