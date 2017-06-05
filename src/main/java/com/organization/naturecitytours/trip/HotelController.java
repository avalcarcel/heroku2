/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.trip;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jhona
 */
@Controller
class HotelController {

    private HotelRepository hotel;

    @Autowired
    public HotelController(HotelRepository hotel) {
        this.hotel = hotel;
    }

    /**
     * Funcion que envia al formulario para crear nuevos hoteles hotelNew.html
     *
     * @param model Se envia un objeto hotel vacio y que se llenará en la
     * template
     * @return hotelNew.hmtl
     */
    @RequestMapping("/hotel/new")
    public String HotelNew(Map<String, Object> model) {

        Hotel hotel = new Hotel();
        model.put("hotel", hotel);

        return "/hotel/hotelNew";
    }

    /**
     * Recibe el objeto Hoten con información, sí los datos introducidos son
     * correctos redirecciona a la lista de hoteles si no vuelve al formulario y
     * envia los errores
     *
     * @param hotel Anotación para validar los campos del objeto
     * @param result Clase con toda la lista de los errores generados
     * @return hotelNew.html o al controlador de lista de hoteles
     */
    @RequestMapping(value = "/hotel/new", method = RequestMethod.POST)
    public String addHotel(@Valid Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "/hotel/hotelNew";
        } else {
            this.hotel.save(hotel);
            return "redirect:/hotel/list";
        }
    }

    /**
     * Controlador de lista de hoteles
     *
     * @param model Envia la lista de hoteles
     * @return hotelList.html
     */
    @RequestMapping("/hotel/list")
    public String HotelList(Map<String, Object> model) {

        Collection<Hotel> h = this.hotel.findAll();
        model.put("list", h);
        return "/hotel/hotelList";
    }

    /**
     * Elimniar hotel por ID
     *
     * @param hotelId recibimos por parametro la ID del hotel que se va eliminar
     * @return Controlador de lista de hoteles
     */
    @RequestMapping("/hotel/delete/{id}")
    public String deleteTrip(@PathVariable("id") String hotelId) {
        Long idhotel = Long.parseLong(hotelId);
        this.hotel.removeById(idhotel);

        return "redirect:/hotel/list";
    }

}
