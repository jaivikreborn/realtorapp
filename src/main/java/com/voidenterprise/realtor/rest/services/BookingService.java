package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Booking;
import com.voidenterprise.realtor.models.Client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    Booking addBooking(Booking booking);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByEmail(String email);

    List<Booking> getBookingsByRestaurant(String restaurantId);
}
