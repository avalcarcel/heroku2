/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import org.springframework.data.repository.Repository;

/**
 *
 * @author Jhona
 */
public interface DateRepository extends Repository<DateTrip, Integer> {

    void save(DateTrip date);
}
