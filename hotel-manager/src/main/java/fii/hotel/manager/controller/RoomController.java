package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.RoomDto;
import fii.hotel.manager.mapper.RoomMapper;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Rooms requests")
public class RoomController {
    private RoomService roomService;
    private RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @ApiOperation(value = "Add a new room")
    @ApiResponse(code = 200, message = "Room successfully added")
    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomDto add(@RequestBody RoomDto roomDto) {
        Room room = roomMapper.map(roomDto);
        room = roomService.save(room);
        return roomMapper.map(room);
    }

    @ApiOperation(value = "Get list of all rooms")
    @ApiResponse(code = 200, message = "List of all rooms")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomDto> getAll() {
        List<Room> rooms = roomService.getAll();
        return rooms.stream().map(roomMapper::map).collect(Collectors.toList());
    }

    @ApiOperation(value = "Get room with specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Room not found"),
            @ApiResponse(code = 200, message = "Retrieved room with the asked id")
    })
    @GetMapping(value = "/{id}")
    public RoomDto getById(@PathVariable Long id) {
        Room room = roomService.getById(id);
        return roomMapper.map(room);
    }
}
