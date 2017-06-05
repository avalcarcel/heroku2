/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.user;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhona
 */
public interface UserRepository extends Repository<User, Long> {

    /**
     * Retrieve
     *
     * @param email email para buscar un usuario
     * @return devuelve el usuario si lo encuentra
     */
    @Transactional(readOnly = true)
    User findByEmail(String email);

    void save(User user);
}
