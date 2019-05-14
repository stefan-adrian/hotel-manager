package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.CategoryDto;
import fii.hotel.manager.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category map(CategoryDto categoryDto){
        Category category=new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setBeds(categoryDto.getBeds());
        category.setTv(categoryDto.getTv());
        category.setPrice(categoryDto.getPrice());
        category.setSize(categoryDto.getSize());
        category.setDescription(categoryDto.getDescription());
        category.setImage(categoryDto.getImage());
        return category;
    }

    public CategoryDto map(Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setBeds(category.getBeds());
        categoryDto.setTv(category.getTv());
        categoryDto.setPrice(category.getPrice());
        categoryDto.setSize(category.getSize());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setImage(category.getImage());
        return categoryDto;

    }
}
