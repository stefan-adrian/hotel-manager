package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.SpaTypeDto;
import fii.hotel.manager.service.SpaTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spa-types")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Spa types requests")
public class SpaTypeController {
    private SpaTypeService spaTypeService;

    @Autowired
    public SpaTypeController(SpaTypeService spaTypeService) {
        this.spaTypeService = spaTypeService;
    }

    @ApiOperation(value = "Add a new spa type")
    @ApiResponse(code = 200, message = "Spa type successfully added")
    @PostMapping
    public SpaTypeDto add(@RequestBody SpaTypeDto spaTypeDto) {
        return spaTypeService.add(spaTypeDto);
    }

    @ApiOperation(value = "Get list of all spa types")
    @ApiResponse(code = 200, message = "List of all spa types")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpaTypeDto> getAll() {
        return spaTypeService.getAllDtos();
    }
}
