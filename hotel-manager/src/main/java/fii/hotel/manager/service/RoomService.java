package fii.hotel.manager.service;

import fii.hotel.manager.model.Room;

import java.util.List;

public interface RoomService {

    Room save(Room room);

    List<Room> getAll();

    Room getById(Long id);
}
