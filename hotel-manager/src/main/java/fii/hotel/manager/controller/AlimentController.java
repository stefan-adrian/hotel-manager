package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.AlimentDto;
import fii.hotel.manager.mapper.AlimentMapper;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.service.AlimentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aliments")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
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

    @ApiOperation(value = "Get list of all aliments")
    @ApiResponse(code = 200, message = "List of all aliments")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlimentDto> getAll(){
        List<Aliment> aliments=alimentService.getAll();
        return aliments.stream().map(alimentMapper::map).collect(Collectors.toList());
    }


}
