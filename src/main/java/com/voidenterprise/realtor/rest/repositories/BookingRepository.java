package com.voidenterprise.realtor.rest.repositories;

import com.voidenterprise.realtor.models.Booking;
import com.voidenterprise.realtor.models.Client;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends CrudRepository<Booking,String> {

    @Query("Select * from bookings where id=?0 limit 1")
    Booking findById(String id);

    @Query("Select * from bookings")
    List<Booking> findAll();

    @Query("Select * from bookings where email=?0 allow filtering")
    List<Booking> findByEmail(String email);

    @Query("Select * from bookings where restaurantid=?0 allow filtering")
    List<Booking> getAllBookingsByRestaurant(UUID restaurantId);
}
