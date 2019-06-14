package fii.hotel.manager.controller;


import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.AllRoomservicesDto;
import fii.hotel.manager.dto.RoomserviceDto;
import fii.hotel.manager.service.RoomserviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-services")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Room service requests")
public class RoomserviceController {

    private RoomserviceService roomserviceService;

    @Autowired
    public RoomserviceController(RoomserviceService roomserviceService) {
        this.roomserviceService = roomserviceService;
    }

    @ApiOperation(value = "Get room services for customer with specified email")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 200, message = "Retrieved room services for customer with the asked email")
    })
    @GetMapping()
    public List<RoomserviceDto> getRoomServicesForCustomerByEmail(@RequestParam String email) {
        return roomserviceService.getAllRoomservicesDtosForCustomerByEmail(email);
    }

    @ApiOperation(value = "Get all room service")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 200, message = "Retrieved all room services")
    })
    @GetMapping("/all")
    public AllRoomservicesDto getAllRoomservices() {
        return roomserviceService.getAllRoomservices();
    }
}
