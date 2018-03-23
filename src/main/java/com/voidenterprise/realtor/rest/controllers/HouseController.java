package com.voidenterprise.realtor.rest.controllers;


import com.voidenterprise.realtor.models.House;
import com.voidenterprise.realtor.models.Realtor;
import com.voidenterprise.realtor.models.request.RealtorRequest;
import com.voidenterprise.realtor.rest.exception.ResourceConstraintViolationException;
import com.voidenterprise.realtor.rest.exception.ResourceNotFoundException;
import com.voidenterprise.realtor.rest.exception.RestBadReqeustException;
import com.voidenterprise.realtor.rest.services.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("v1")
public class HouseController {

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @Autowired
    HouseService houseService;


    @PostMapping("/houses")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
    @ResponseBody
    public ResponseEntity createHouse(
            @Valid @RequestBody House house) {
        try {
            log.info("inside realtor :: "+house.toString());
            house.setId(UUID.randomUUID());
            House newHouse = houseService.addHouse(house);
            log.info("newHouse :: "+newHouse.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(newHouse);
        }
        catch (IllegalArgumentException | ResourceConstraintViolationException e) {
            throw new BadRequestException(e);
        }
        catch (NullPointerException  | ResourceNotFoundException e) {
            throw new InternalServerErrorException(e);
        }
    }


    @GetMapping("/houses")
    @ResponseBody
    public List<House> getHouses(){
      List<House> houses = houseService.getAllHouses();
            return houses;
    }


}
