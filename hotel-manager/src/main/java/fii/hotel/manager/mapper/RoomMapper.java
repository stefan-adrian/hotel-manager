package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.RoomDto;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    private CategoryMapper categoryMapper;
    private CategoryService categoryService;

    public RoomMapper(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    public Room map(RoomDto roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setFloor(roomDto.getFloor());
        room.setName(roomDto.getName());
        if(roomDto.getCategoryDto()!=null) {
            room.setCategory(categoryService.getById(roomDto.getCategoryDto().getId()));
        }
        return room;
    }

    public RoomDto map(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setFloor(room.getFloor());
        roomDto.setName(room.getName());
        if(room.getCategory()!=null) {
            roomDto.setCategoryDto(categoryMapper.map(room.getCategory()));
        }
        return roomDto;
    }
}
