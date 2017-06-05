/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import java.awt.print.Pageable;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mark
 */
public interface TripRepository extends Repository<Trip, Integer> {

    /**
     * mètode per guardar un nou viatge a la base de dades
     *
     * @param trip
     */
    void save(Trip trip);

    /**
     * Query para seleccionar un Trip de la Bade de datos
     *
     * @param id the id to search for
     * @return the {@link Owner} if found
     * @Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE
     * owner.id =:id")
     *
     * @Transactional(readOnly = true) Trip findById(@Param("id") Integer id);
     */
    @Transactional(readOnly = true)
    Trip findById(@Param("tripId") int id);

    /**
     *
     * @param name
     * @return java.util.Collection de viatges
     */
    @Query("SELECT DISTINCT trip FROM Trip trip WHERE trip.name LIKE :name%")
    @Transactional(readOnly = true)
    Collection<Trip> findByName(@Param("name") String name);

    @Transactional(readOnly = true)
    Collection<Trip> findAll();

    @Modifying
    @Query("DELETE Trip trip WHERE trip.id = :id")
    @Transactional(readOnly = false)
    void removeById(@Param("id") int id);

    @Query(value = "SELECT * FROM Trip order by rand() limit 4",
            nativeQuery = true)

    @Transactional(readOnly = true)
    Collection<Trip> randomTrip();

}
