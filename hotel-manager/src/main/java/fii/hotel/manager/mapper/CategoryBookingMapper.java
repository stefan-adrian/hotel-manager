package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryBookingMapper {

    public CategoryBookingDto map(Category category,Integer availableRooms){
        CategoryBookingDto categoryBookingDto=new CategoryBookingDto();
        categoryBookingDto.setName(category.getName());
        categoryBookingDto.setBeds(category.getBeds());
        categoryBookingDto.setTv(category.getTv());
        categoryBookingDto.setCategoryBasicPrice(category.getPrice());
        categoryBookingDto.setImage(category.getImage());
        categoryBookingDto.setSize(category.getSize());
        categoryBookingDto.setDescription(category.getDescription());
        categoryBookingDto.setTotalRooms(category.getRooms().size());
        categoryBookingDto.setAvailableRooms(availableRooms);
        return categoryBookingDto;
    }
}
