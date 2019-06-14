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
        category.setOccupancy(categoryDto.getOccupancy());
        category.setKitchen(categoryDto.getKitchen());
        category.setCoffeeMaker(categoryDto.getCoffeeMaker());
        category.setSafe(categoryDto.getSafe());
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
        categoryDto.setOccupancy(category.getOccupancy());
        categoryDto.setKitchen(category.getKitchen());
        categoryDto.setCoffeeMaker(category.getCoffeeMaker());
        categoryDto.setSafe(category.getSafe());
        categoryDto.setPrice(category.getPrice());
        categoryDto.setSize(category.getSize());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setImage(category.getImage());
        return categoryDto;

    }
}
