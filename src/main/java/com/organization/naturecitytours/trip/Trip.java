/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import com.organization.naturecitytours.book.Pax;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Mark
 */
@Entity
@Table(name = "trip")

public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Range(min = 1, max = 9)
    @Column(name = "duration")
    private String duration;

    @Column(name = "pricesingle")
    @DecimalMax("100000.00")
    @NotNull
    private double pricesingle;

    @Column(name = "suplement", nullable = true)
    private Double suplement;

    @Column(name = "departurefirst")
    private Date departurefirst;

    @Column(name = "departurelast")
    private Date departurelast;

    @Column(name = "image")
    private String image;

    @Column(name = "descripcion_es")
    private String descripcion_es;

    @Column(name = "descripcion_ca")
    private String descripcion_ca;

    @Column(name = "descripcion_en")
    private String descripcion_en;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "triphotels", joinColumns = {
        @JoinColumn(name = "idtrip")}, inverseJoinColumns = {
        @JoinColumn(name = "idhotel")})
    private Set<Hotel> hotels;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idtrip", cascade = CascadeType.ALL)
    @OrderBy("departuredates asc")
    private Set<DateTrip> dates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip", cascade = CascadeType.ALL)
    @OrderBy("id asc")
    private Set<Itinerary> itineraries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idtrip")
    private Set<Images> img;

    public Trip() {
    }

    public Trip(String name, String duration, double pricesingle, Double suplement, Set<Hotel> hotels, Set<Itinerary> itineraries) {
        this.name = name;
        this.duration = duration;

        this.pricesingle = pricesingle;
        this.suplement = suplement;
        this.hotels = hotels;
        this.itineraries = itineraries;
    }

    public String getDescripcion_es() {
        return descripcion_es;
    }

    public void setDescripcion_es(String descripcion_es) {
        this.descripcion_es = descripcion_es;
    }

    public String getDescripcion_ca() {
        return descripcion_ca;
    }

    public void setDescripcion_ca(String descripcion_ca) {
        this.descripcion_ca = descripcion_ca;
    }

    public String getDescripcion_en() {
        return descripcion_en;
    }

    public void setDescripcion_en(String descripcion_en) {
        this.descripcion_en = descripcion_en;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPricesingle() {
        return pricesingle;
    }

    public void setPricesingle(double pricesingle) {
        this.pricesingle = pricesingle;
    }

    public Double getSuplement() {
        return suplement;
    }

    public void setSuplement(Double suplement) {
        this.suplement = suplement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Images> getImg() {
        return img;
    }

    public void setImg(Set<Images> img) {
        this.img = img;
    }

    public Set<DateTrip> getDates() {
        return dates;
    }

    public void setDates(Set<DateTrip> dates) {
        this.dates = dates;
    }

    public Set<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(Set<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Date getDeparturefirst() {
        return departurefirst;
    }

    public void setDeparturefirst(Date departurefirst) {
        this.departurefirst = departurefirst;
    }

    public Date getDeparturelast() {
        return departurelast;
    }

    public void setDeparturelast(Date departurelast) {
        this.departurelast = departurelast;
    }

    public void InserFile(MultipartFile file, File imageFile) {
        try {
            byte[] bytes = file.getBytes();

            // Insertamos el archivo en nuestro proyecto
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imageFile));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            System.out.println("ERROR AL SUBIR foto de portada");
        }
    }

}
