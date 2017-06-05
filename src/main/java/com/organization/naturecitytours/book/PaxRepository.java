/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mark
 */
public interface PaxRepository extends Repository<Pax, String> {

    /**
     * Mètode per guardar usuaris
     *
     * @param p
     */
    void save(Pax p);

    /**
     * Mètode per cercar usuaris per id
     */
    @Query("Select pax FROM Pax pax WHERE pax.dni = :dni")
    @Transactional(readOnly = true)
    public Pax findById(@Param("dni") String dni);

    /**
     * Mètode per cercar usuaris per id
     */
    @Query("DELETE Pax pax WHERE pax.id = :id")
    @Transactional(readOnly = false)
    public Pax delete(@Param("id") String id);
}
