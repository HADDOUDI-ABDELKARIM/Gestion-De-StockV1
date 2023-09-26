package com.abdel.gestiondestock.service;

import com.abdel.gestiondestock.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto findById(Integer id);

    CategoryDto findByCodeCategory(String code);

    List<CategoryDto> findAll();

    void delete(Integer id);
}
