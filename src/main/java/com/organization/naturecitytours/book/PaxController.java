/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Mark
 */
@Controller
public class PaxController {

    PaxRepository pax;

    @Autowired
    public PaxController(PaxRepository pax) {
        this.pax = pax;
    }

    public void save(Pax p) {
        this.pax.save(p);
    }

}
