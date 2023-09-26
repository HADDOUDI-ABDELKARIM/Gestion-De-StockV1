package com.abdel.gestiondestock.repository;

import com.abdel.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCode(String code);

}
