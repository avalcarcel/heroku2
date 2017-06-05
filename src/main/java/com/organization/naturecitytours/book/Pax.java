/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.book;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Xavier
 *
 */
@Entity
@Table(name = "pax")
public class Pax implements Serializable {

    @Id
    private String dni;
    @Column(name = "name")
    @NotEmpty
    private String name;
    @Column(name = "surname")
    @NotEmpty
    private String surname;
    @Column(name = "dob")
    @NotNull
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Column(name = "passport")
    @NotEmpty
    private String passport;
    @Column(name = "passportexpiry")
    @NotNull
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date passportexpiry;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookpax", joinColumns = {
        @JoinColumn(name = "dnipax")}, inverseJoinColumns = {
        @JoinColumn(name = "idbook")})
    private Set<Book> books;

    public Pax() {
    }

    public Pax(String dni) {
        this.dni = dni;
    }

    public Pax(String dni, String name, String surname, Date dob, String passport, Date passportexpiry) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.passport = passport;
        this.passportexpiry = passportexpiry;

    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getPassportexpiry() {
        return passportexpiry;
    }

    public void setPassportexpiry(Date passportexpiry) {
        this.passportexpiry = passportexpiry;
    }

}
