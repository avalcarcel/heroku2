/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Xavier
 * @author Marc
 */
@Entity
@Table(name = "itinerary")
public class Itinerary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "day_es")
    private String day_es;

    @Column(name = "day_ca")
    private String day_ca;

    @Column(name = "day_en")
    private String day_en;

    @ManyToOne
    @JoinColumn(name = "idtrip")
    private Trip trip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay_es() {
        return day_es;
    }

    public void setDay_es(String day_es) {
        this.day_es = day_es;
    }

    public String getDay_ca() {
        return day_ca;
    }

    public void setDay_ca(String day_ca) {
        this.day_ca = day_ca;
    }

    public String getDay_en() {
        return day_en;
    }

    public void setDay_en(String day_en) {
        this.day_en = day_en;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
