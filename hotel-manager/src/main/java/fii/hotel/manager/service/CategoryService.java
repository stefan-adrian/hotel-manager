package fii.hotel.manager.service;

import fii.hotel.manager.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    Category save(Category category, MultipartFile image);

    List<Category> getAll();

    Category getById(Long id);
}
