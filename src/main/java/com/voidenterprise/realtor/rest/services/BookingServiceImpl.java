package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Booking;
import com.voidenterprise.realtor.models.Client;
import com.voidenterprise.realtor.rest.repositories.BookingRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Booking addBooking(Booking booking) {

        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByEmail(email);
    }

    @Override
    public List<Booking> getBookingsByRestaurant(String restaurantId) {
        return bookingRepository.getAllBookingsByRestaurant(UUID.fromString(restaurantId));
    }
}
