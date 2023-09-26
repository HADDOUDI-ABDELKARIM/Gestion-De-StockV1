package com.abdel.gestiondestock.controller;

import com.abdel.gestiondestock.DTO.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abdel.gestiondestock.utile.Constants.APP_ROOT;
public interface CategoryController {


    @PostMapping(value = APP_ROOT+"/categories/create",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto dto) ;


    @GetMapping(value = APP_ROOT+"/categories/{idcategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idcategory") Integer id);


    @GetMapping(value = APP_ROOT+"/categories/{codecategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCodeCategory(@PathVariable("codecategory") String codeCategory);


    @GetMapping(value = APP_ROOT+"/categories/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();


    @DeleteMapping(value = APP_ROOT+"categorise/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Integer id);
}
