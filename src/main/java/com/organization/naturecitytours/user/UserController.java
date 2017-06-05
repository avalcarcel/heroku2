/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organization.naturecitytours.user;

import com.organization.naturecitytours.book.Book;
import com.organization.naturecitytours.book.BookRepository;
import com.organization.naturecitytours.book.Pax;
import com.organization.naturecitytours.trip.DateTrip;
import com.organization.naturecitytours.trip.Trip;
import com.organization.naturecitytours.trip.TripRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jhona
 * @author xavi
 */
@Controller
public class UserController {

    private UserRepository user;
    private BookRepository book;
    private TripRepository trip;
    // private User us = new User();

    @Autowired
    public UserController(UserRepository user, BookRepository book, TripRepository trip) {
        this.user = user;
        this.book = book;
        this.trip = trip;
    }

    /**
     * Metodo que redirecciona al formulario de 'login'
     *
     * @param model añadimos un nuevo usuario y se le pasa a la template
     * userSession.html
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String initSession(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "user/userSession";
    }

    /**
     * Metodo recoge la información del login en un nuevo usuario 'user' Busca
     * el usuario por Email en la base de datos 'findByEmail' sí el usuario
     * existe compara las contraseñas,si no existe vuelta al login sí la
     * contraseña son iguales , crea una nueva SESSION y redirige a su perfil
     *
     * @param user crea un User con la información del formulario
     * @param result
     * @param session Crea una nueva sessión
     * @return Profile de la agencia / login
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String initSession(@Valid User user, BindingResult result, HttpSession session, @RequestParam("lang") String lang) {
        try {
            User user1 = this.user.findByEmail(user.getEmail());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            boolean matches = passwordEncoder.matches(user.getPassword(), user1.getPassword());

            System.out.println("Email del usuario devuelto: " + user1.getEmail());

            if (matches) {

                session.setAttribute("user", user1.getEmail());
                session.setAttribute("userID", user1.getId());
                session.setAttribute("lang", lang);
                return "user/userProfile";

            } else {

                System.out.println("Error en contraseña");;
                return "user/userSession";
            }

        } catch (Exception e) {
            System.out.println("Error en correo");
            return "user/userSession";

        }

    }

    /**
     * Metodo para cerrar sessión
     *
     * @param session
     * @return
     */
    @RequestMapping("/user/logout")
    public String closeSession(HttpSession session) {
        session.setAttribute("user", null);
        return "index";
    }

    /**
     * Metodo que redirecciona al perfil de la agencia
     *
     * @return
     */
    @RequestMapping("/user/profile")
    public String profile() {
        return "/user/userProfile";
    }

    /**
     * Metodo para crear un nuevo usuario, crear el objeto User.
     *
     * @param model añadimos un nuevo Objeto User, que luego lo recogeremos en
     * el metodo POST
     * @return a to
     */
    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "user/userNew";
    }

    /**
     * Metodo que guarda un nuevo usuario 'User' en la base de datos encryptando
     * la la contraseña
     *
     * @param user recojemos el User de formulario
     * @param result
     * @return redirige a UserSession si el registro se a hecho correctamente o
     * al formuralio si hay algun error
     */
    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {

            return "user/userNew";

        } else {

            String cryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

            user.setPassword(cryptedPassword);

            this.user.save(user);

            return "user/userSession";
        }
    }

    /**
     * Metode que et redirecciona a una vista que retorna una llista amb totes
     * les reserves fetes per l'Usuari.
     *
     * @param book
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/book/listBooksUser")
    public String list(Book book, BindingResult result, Map<String, Object> model, HttpSession session) {
        User u = this.user.findByEmail(session.getAttribute("user").toString());
        Collection<Book> books = u.getBooks();
        if (books.isEmpty()) {
            // no trips found
            result.rejectValue("id", "notFound", "not found");
            return "book/listBooksUser";
        } else {
            if (session.getAttribute("user") != null) {
                model.put("selections", books);
                return "book/listBooksUser";
            } else {
                return "book/listBooksUser";
            }

        }
    }

    /**
     * Metode que serveix per pasar les Dates del viatge i la seva ID per fer la
     * reserva.
     *
     * @param model
     * @param idTrip
     * @return
     */
    @RequestMapping("/book/form/{id}")
    public String welcome4(Map<String, Object> model, @PathVariable("id") String idTrip) {

        int id = Integer.parseInt(idTrip);
        Trip t = this.trip.findById(id);

        Set<DateTrip> dates = t.getDates();

        model.put("dates", dates);
        model.put("idTrip", idTrip);
        System.out.println("id trip " + idTrip);

        return "book/bookForm";
    }

    /**
     * Metode que et redirecciona a la vista d'about.html
     *
     * @return
     */
    @RequestMapping("/user/about")
    public String about() {
        return "/about";
    }
}
