package com.voidenterprise.realtor.rest.controllers;


import com.voidenterprise.realtor.models.Booking;
import com.voidenterprise.realtor.models.Client;
import com.voidenterprise.realtor.models.response.BookingResponse;
import com.voidenterprise.realtor.rest.exception.ResourceConstraintViolationException;
import com.voidenterprise.realtor.rest.exception.ResourceNotFoundException;
import com.voidenterprise.realtor.rest.exception.RestBadReqeustException;
import com.voidenterprise.realtor.rest.services.BookingService;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("v1")
public class BookingController {

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @Autowired
    BookingService bookingService;

    @Autowired
    RestaurantService restaurantService;


    @PostMapping("/bookings")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
    @ResponseBody
    public ResponseEntity createBooking(
            @Valid @RequestBody Booking booking) {
        try {
            log.info("inside Booking :: "+booking.toString());
            booking.setId(UUID.randomUUID());

            Booking newBooking = bookingService.addBooking(booking);
            log.info("newBooking :: "+newBooking.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
        }
        catch (IllegalArgumentException | ResourceConstraintViolationException e) {
            throw new BadRequestException(e);
        }
        catch (NullPointerException  | ResourceNotFoundException e) {
            throw new InternalServerErrorException(e);
        }
    }

    @GetMapping("/customer/bookings")
    @ResponseBody
    public ResponseEntity getCustomerBooking(
            @RequestParam("email")  String email
    ){
        List<Booking> bookings =  bookingService.getBookingsByEmail(email);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for(Booking booking : bookings){
            bookingResponses.add(new BookingResponse(booking,restaurantService.getRestaurantById(booking.getRestaurantId().toString())));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponses);
    }


    @GetMapping("/restaurants/{restaurantId}/bookings")
    @ResponseBody
    public List<Booking> getRestaurantBookings(
            @PathVariable("restaurantId")  String restaurantId
    ){
      List<Booking> bookings = bookingService.getBookingsByRestaurant(restaurantId);
            return bookings;
    }



}
