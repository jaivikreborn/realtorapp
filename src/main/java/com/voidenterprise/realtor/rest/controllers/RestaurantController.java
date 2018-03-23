package com.voidenterprise.realtor.rest.controllers;


import com.voidenterprise.realtor.models.Restaurant;
import com.voidenterprise.realtor.rest.exception.ResourceConstraintViolationException;
import com.voidenterprise.realtor.rest.exception.ResourceNotFoundException;
import com.voidenterprise.realtor.rest.services.RestaurantService;
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
public class RestaurantController {

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @Autowired
    RestaurantService restaurantService;


    @PostMapping("/restaurants")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
    @ResponseBody
    public ResponseEntity createRestaurant(
            @Valid @RequestBody Restaurant restaurant) {
        try {
            log.info("inside Restaurant :: "+restaurant.toString());
            restaurant.setId(UUID.randomUUID());
            Restaurant newrestaurant = restaurantService.addRestaurant(restaurant);
            log.info("newrestaurant :: "+newrestaurant.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(newrestaurant);
        }
        catch (IllegalArgumentException | ResourceConstraintViolationException e) {
            throw new BadRequestException(e);
        }
        catch (NullPointerException  | ResourceNotFoundException e) {
            throw new InternalServerErrorException(e);
        }
    }


    @GetMapping("/restaurants")
    @ResponseBody
    public List<Restaurant> getRestaurants(){
      List<Restaurant> restaurants = restaurantService.getAllRestaurants();
            return restaurants;
    }


}
