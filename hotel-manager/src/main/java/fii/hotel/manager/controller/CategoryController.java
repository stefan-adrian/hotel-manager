package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.dto.CategoryDto;
import fii.hotel.manager.dto.RoomDto;
import fii.hotel.manager.mapper.CategoryMapper;
import fii.hotel.manager.model.Category;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.service.CategoryService;
import fii.hotel.manager.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Categories requests")
public class CategoryController {
    private CategoryMapper categoryMapper;
    private CategoryService categoryService;
    private PaymentService paymentService;

    @Autowired
    public CategoryController(CategoryMapper categoryMapper, CategoryService categoryService, PaymentService paymentService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
        this.paymentService = paymentService;
    }

    @ApiOperation(value = "Add a new category")
    @ApiResponse(code = 200, message = "Category successfully added")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto add(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.map(categoryDto);
        category = categoryService.save(category);
        return categoryMapper.map(category);
    }

    @ApiOperation(value = "Get category with specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 200, message = "Retrieved category with the asked id")
    })
    @GetMapping(value = "/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return categoryMapper.map(category);
    }


    @ApiOperation(value = "Get list of all categories")
    @ApiResponse(code = 200, message = "List of all categories")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryService.getAll();
        return categories.stream().map(categoryMapper::map).collect(Collectors.toList());
    }

    @ApiOperation(value = "Add image to a existing category")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 204, message = "Category successfully added")
    })
    @PatchMapping("/{id}/image")
    public ResponseEntity<Object> uploadCategoryImage(@RequestPart("image") MultipartFile image, @PathVariable Long id) {
        Category category = categoryService.getById(id);
        categoryService.save(category,image);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get category image")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 200, message = "Category image")
    })
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getCategoryImage(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + category.getName() + ".jpeg\"")
                .body(new ByteArrayResource(category.getImage()));

    }

    @ApiOperation(value = "Get list of all rooms available between dates")
    @ApiResponse(code = 200, message = "List of all rooms available between dates")
    @GetMapping(value = "/available")
    public List<CategoryBookingDto> getAllCategoriesAvailableBetweenDates(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate arrivalDate,
                                                                          @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate departureDate,
                                                                          @RequestParam String email){
        return categoryService.getAllCategoriesAvailableBetweenDates(arrivalDate,departureDate,email);
    }


    @GetMapping(value = "/test")
    public String test(){
        return paymentService.createPayment();
    }

    @GetMapping(value = "/test2")
    public void test2(){
        paymentService.doPay();
    }

}
