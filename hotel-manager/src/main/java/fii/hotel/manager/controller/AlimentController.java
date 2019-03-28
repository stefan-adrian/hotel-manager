package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.AlimentDto;
import fii.hotel.manager.mapper.AlimentMapper;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.service.AlimentService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<AlimentDto> getAll() {
        List<Aliment> aliments = alimentService.getAll();
        return aliments.stream().map(alimentMapper::map).collect(Collectors.toList());
    }

    @ApiOperation(value = "Add image to a existing aliment")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Aliment not found"),
            @ApiResponse(code = 204, message = "Image successfully added")
    })
    @PatchMapping("/{id}/image")
    public ResponseEntity<Object> uploadAlimentImage(@RequestPart("image") MultipartFile image, @PathVariable Long id) {
        Aliment aliment = alimentService.getById(id);
        alimentService.save(aliment, image);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get aliment image")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Alimnt not found"),
            @ApiResponse(code = 200, message = "Aliment image")
    })
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getAlimentImage(@PathVariable Long id) {
        Aliment aliment = alimentService.getById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + aliment.getName() + ".jpeg\"")
                .body(new ByteArrayResource(aliment.getImage()));

    }
}
