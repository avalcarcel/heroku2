/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhona
 */
public interface HotelRepository extends Repository<Hotel, Integer> {

    @Transactional(readOnly = true)
    Collection<Hotel> findAll();

    @Transactional(readOnly = true)
    Hotel findById(@Param("hotelId") Long id);

    @Modifying
    @Query("DELETE Hotel hotel WHERE hotel.id = :id")
    @Transactional(readOnly = false)
    void removeById(@Param("id") Long id);

    void save(Hotel hotel);
}
