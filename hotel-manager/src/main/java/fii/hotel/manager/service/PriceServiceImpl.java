package fii.hotel.manager.service;

import fii.hotel.manager.model.Category;
import fii.hotel.manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PriceServiceImpl implements  PriceService{

    private CategoryRepository categoryRepository;

    @Autowired
    public PriceServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getAllCategoriesFetchingRoomsFetchingBookings() {
        return categoryRepository.findAllCategoriesFetchingRoomsFetchingBookings();
    }
}
