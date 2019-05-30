package fii.hotel.manager.service;

import fii.hotel.manager.model.Category;

import java.util.Set;

public interface PriceService {

    Set<Category> getAllCategoriesFetchingRoomsFetchingBookings();
}
