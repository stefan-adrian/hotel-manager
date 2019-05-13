package fii.hotel.manager.service;

import fii.hotel.manager.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {

    Room save(Room room);

    List<Room> getAll();

    Room getById(Long id);

    Room getByIdFetchBookings(Long id);

    void checkThatRoomBookingTimeDoesNotOverlap(Room room, LocalDateTime startTime, LocalDateTime endTime);
}
