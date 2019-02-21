package fii.hotel.manager.service;

import fii.hotel.manager.exception.RoomNotFoundException;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room save(Room room) {
        Room roomSaved = roomRepository.save(room);
        logger.debug("New room " + roomSaved.getName() + " with id " + roomSaved.getId() + " was saved in the database.");
        return roomSaved;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        logger.debug("Retrieved all rooms from database.");
        return rooms;
    }

    @Override
    public Room getById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            logger.debug("Room " + room.getName() + " with id " + room.getId() + " has benn retrieved from database.");
            return room;
        } else {
            logger.error("Room with id " + id + " was not found in the database.");
            throw new RoomNotFoundException(id);
        }
    }
}
