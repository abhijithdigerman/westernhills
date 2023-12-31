package com.westernhills.westernhills.service.interfaceService;

import com.westernhills.westernhills.entity.admin.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    Category getCategory(UUID uuid);

    List<Category> findAll();
    Category save( Category category);
    Optional<Category> findById(UUID id);
    Category upDate(Category category);
    void deleteById(UUID id);
    void enableById(UUID id);

    public List<Category> getAllCategories();
    List<Category> findAllByActivated();

    void updateCategory(UUID id, Category user);

   Optional<Category> findByName(String username);


    public Optional<Category> getCategoryById(UUID id);



}
