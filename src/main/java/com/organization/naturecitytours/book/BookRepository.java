/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.book;

import com.organization.naturecitytours.book.Book;
import com.organization.naturecitytours.user.User;
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
public interface BookRepository extends Repository<Book, Long> {

    /**
     * Mètode per guardar una nova reserva a base de dades
     *
     * @param b
     */
    void save(Book b);

    /**
     * Mètode que busca reserves per email d'usuari
     *
     * @param id
     * @return java.util.Collection de reserves
     */
    @Query("SELECT DISTINCT user FROM User user WHERE user.email LIKE :email%")
    @Transactional(readOnly = true)
    public Collection<User> findByEmail(@Param("email") String email);

    @Transactional(readOnly = true)
    public Book findById(@Param("id") int id);

    /**
     * Mètode que retorna totes les reserves
     *
     * @return
     */
    @Transactional(readOnly = true)
    public Collection<Book> findAll();

    /**
     * Mètode per esborrar una reserva
     *
     * @param id
     */
    @Modifying
    @Query("DELETE Book book WHERE book.id = :id")
    @Transactional(readOnly = false)
    public void delete(@Param("id") Long id);
}
