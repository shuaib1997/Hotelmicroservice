package com.lcwd.hotel.service.services.impl;

import com.lcwd.hotel.service.entities.Hotel;
import com.lcwd.hotel.service.repositories.HotelRepository;
import com.lcwd.hotel.service.services.HotelService;
import com.lcwd.hotel.service.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
       String randomId= UUID.randomUUID().toString();
       hotel.setId(randomId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotel=this.hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found !! : S"+hotelId));
        return hotel;
    }

    @Override
    public void deleteHotel(String hotelId) {
        this.hotelRepository.deleteById(hotelId);
        }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return null;
    }
}
