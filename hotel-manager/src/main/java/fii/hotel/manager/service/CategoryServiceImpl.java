package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.exception.CategoryNotFoundException;
import fii.hotel.manager.exception.NoRoomsAvailableForBookingException;
import fii.hotel.manager.mapper.CategoryBookingMapper;
import fii.hotel.manager.model.Category;
import fii.hotel.manager.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private PriceService priceService;
    private CategoryBookingMapper categoryBookingMapper;
    private RoomService roomService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, PriceService priceService, CategoryBookingMapper categoryBookingMapper, RoomService roomService) {
        this.categoryRepository = categoryRepository;
        this.priceService = priceService;
        this.categoryBookingMapper = categoryBookingMapper;
        this.roomService = roomService;
    }

    @Override
    public Category save(Category category) {
        Category categorySaved = categoryRepository.save(category);
        logger.debug("New category " + categorySaved.getName() + " with id " + categorySaved.getId() + " was saved in the database.");
        return categorySaved;
    }

    @Override
    public Category save(Category category, MultipartFile image) {
        try{
            category.setImage(image.getBytes());
        } catch (IOException e){
            logger.error("Image for category with id " + category.getId() + " could not be saved in the database.");
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        logger.debug("Retrieved all categories from database.");
        return categories;
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            logger.debug("Category " + category.getName() + " with id " + category.getId() + " has benn retrieved from database.");
            return category;
        } else {
            logger.error("Category with id " + id + " was not found in the database.");
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public List<CategoryBookingDto> getAllCategoriesAvailableBetweenDates(LocalDate arrivalDate, LocalDate departureDate) {
        List<CategoryBookingDto> categoryBookingDtos = new ArrayList<>();
        categoryRepository.findAllCategoriesFetchingRoomsFetchingBookings().forEach(category -> {
            Integer categoryAvailableRooms=roomService.getNumberOfAvailableRoomsBetweenDates(category.getRooms(),arrivalDate,departureDate);
            if(categoryAvailableRooms>0) {
                categoryBookingDtos.add(categoryBookingMapper.map(category, categoryAvailableRooms));
            }
        });
        if (categoryBookingDtos.size() == 0) {
            logger.error("There are no rooms available for booking between " + arrivalDate + " and " + departureDate);
            throw new NoRoomsAvailableForBookingException(arrivalDate, departureDate);
        }
        setCategoryBookingTotalPrice(categoryBookingDtos,arrivalDate,departureDate);
        return categoryBookingDtos;
    }

    private void setCategoryBookingTotalPrice(List<CategoryBookingDto> categoryBookingDtos,LocalDate arrivalDate, LocalDate departureDate){
        long bookingDays = DAYS.between(arrivalDate, departureDate);
        categoryBookingDtos.forEach(categoryBookingDto -> categoryBookingDto.setTotalBookingPrice(categoryBookingDto.getCategoryBasicPrice()*bookingDays));
    }
}
