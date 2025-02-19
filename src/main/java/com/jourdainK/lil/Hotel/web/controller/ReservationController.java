package com.jourdainK.lil.Hotel.web.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jourdainK.lil.Hotel.data.entity.Reservation;
import com.jourdainK.lil.Hotel.data.repository.ReservationRepository;

import org.springframework.ui.Model;
import io.micrometer.common.util.StringUtils;


@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;


    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }




    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getReservationsByDate(@RequestParam(value="reservDate", required=false) String reservDate, Model model) {
        Date date = null;

        if(StringUtils.isNotEmpty(reservDate)){
            date = Date.valueOf(reservDate);
        }else{
            date = new Date(new java.util.Date().getTime());
        }

        List<Reservation> reservations = this.reservationRepository.findByReservDate(date);
        model.addAttribute("reservations", reservations);
        model.addAttribute("selectedDate", date);

        return "reservations";
    }
    
}
