package com.voidenterprise.realtor.models.response;

import com.voidenterprise.realtor.models.Booking;
import com.voidenterprise.realtor.models.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class BookingResponse implements Serializable {

    private UUID bookingId;
    private Booking bookingDetails;
    private Restaurant restaurant;

    public BookingResponse(Booking booking, Restaurant restaurant) {
        this.bookingId = booking.getId();
        this.restaurant = restaurant;
        this.bookingDetails = booking;
    }
}


