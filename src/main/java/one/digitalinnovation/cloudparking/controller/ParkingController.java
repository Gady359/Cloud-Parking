package one.digitalinnovation.cloudparking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.model.Parking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import one.digitalinnovation.cloudparking.service.ParkingService;


import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }


    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){

       List<Parking> parkingList=parkingService.findAll();
       List<ParkingDTO> result =parkingMapper.toParkingDTOList(parkingList);
       return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){

        Parking parking=parkingService.findById(id);
        ParkingDTO  result =parkingMapper.toparkingDTO(parking);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking=parkingService.create(parkingCreate);
        var result =parkingMapper.toparkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id,@RequestBody ParkingCreateDTO parkingCreateDTO){
        var parkingUpdate = parkingMapper.toParkingCreate(parkingCreateDTO);
        var parking=parkingService.update(id,parkingUpdate);
        return ResponseEntity.ok(parkingMapper.toparkingDTO(parking));

    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> checkOut (@PathVariable String id){

        Parking parking=parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toparkingDTO(parking));

    }
}
