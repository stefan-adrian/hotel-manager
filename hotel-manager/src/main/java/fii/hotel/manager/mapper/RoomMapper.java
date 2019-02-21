package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.RoomDto;
import fii.hotel.manager.model.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    public Room map(RoomDto roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setBeds(roomDto.getBeds());
        room.setFloor(roomDto.getFloor());
        room.setPrice(roomDto.getPrice());
        room.setTv(roomDto.getTv());
        room.setName(roomDto.getName());
        return room;
    }

    public RoomDto map(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setBeds(room.getBeds());
        roomDto.setFloor(room.getFloor());
        roomDto.setName(room.getName());
        roomDto.setTv(room.getTv());
        roomDto.setPrice(room.getPrice());
        return roomDto;
    }
}
