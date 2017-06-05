/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import org.springframework.data.repository.Repository;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhona
 */
public interface ImagesRepository extends Repository<Images, Integer> {

    /**
     * Metodo que guarda una imagen en la bade de datos
     *
     * @param images
     */
    void save(Images image);

    @Transactional(readOnly = true)
    Collection<Images> findById(Integer id);

}
