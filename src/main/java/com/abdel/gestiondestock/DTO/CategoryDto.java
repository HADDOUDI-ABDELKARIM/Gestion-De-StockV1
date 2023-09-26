package com.abdel.gestiondestock.DTO;

import com.abdel.gestiondestock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    public static CategoryDto fromEntity(Category category){
        if(category == null){
            return null;
            //TODO throw exception
        }
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .idEntreprise(category.getIdEntreprise())
                .designation(category.getDesignation()).build();
    }

    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto==null){
            return null;
        }
        Category category =new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setIdEntreprise(categoryDto.getIdEntreprise());
        category.setDesignation(categoryDto.getDesignation());
        return category;
    }

}
