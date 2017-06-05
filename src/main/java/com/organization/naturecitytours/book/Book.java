/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.book;

import com.organization.naturecitytours.trip.Trip;
import com.organization.naturecitytours.user.User;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Xavier
 * @author Marc
 */
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    @NotNull
    private Date date;
    @Column(name = "num_pax")
    @NotNull
    private int num_Pax;
    @Column(name = "pvp")
    @NotNull
    private double pvp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtrip")
    @NotNull
    private Trip idtrip;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookpax", joinColumns = {
        @JoinColumn(name = "idbook")}, inverseJoinColumns = {
        @JoinColumn(name = "dnipax")})
    private Set<Pax> paxs;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookuser", joinColumns = {
        @JoinColumn(name = "idbook")}, inverseJoinColumns = {
        @JoinColumn(name = "iduser")})
    public Set<User> users;

    public Book() {
    }

    public Book(Date date, int num_Pax, double pvp, Trip idtrip, Set<Pax> paxs, Set<User> users) {
        this.date = date;
        this.num_Pax = num_Pax;
        this.pvp = pvp;
        this.idtrip = idtrip;
        this.paxs = paxs;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNum_Pax() {
        return num_Pax;
    }

    public void setNum_Pax(int num_Pax) {
        this.num_Pax = num_Pax;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public Trip getIdtrip() {
        return idtrip;
    }

    public void setIdtrip(Trip idtrip) {
        this.idtrip = idtrip;
    }

    public Set<Pax> getPaxs() {
        return paxs;
    }

    public void setPaxs(Set<Pax> paxs) {
        this.paxs = paxs;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    void setDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
