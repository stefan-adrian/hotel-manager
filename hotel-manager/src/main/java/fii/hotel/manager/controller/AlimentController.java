package fii.hotel.manager.controller;

import fii.hotel.manager.dto.AlimentDto;
import fii.hotel.manager.mapper.AlimentMapper;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.service.AlimentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aliments")
@Api(description = "Aliments requests")
public class AlimentController {
    private AlimentService alimentService;
    private AlimentMapper alimentMapper;

    @Autowired
    public AlimentController(AlimentService alimentService, AlimentMapper alimentMapper) {
        this.alimentService = alimentService;
        this.alimentMapper = alimentMapper;
    }

    @ApiOperation(value = "Add a new aliment")
    @ApiResponse(code = 200, message = "Aliment successfully added")
    @PostMapping
    public AlimentDto add(@RequestBody AlimentDto alimentDto) {
        Aliment aliment = alimentMapper.map(alimentDto);
        aliment = alimentService.save(aliment);
        return alimentMapper.map(aliment);
    }
}
