package com.jourdainK.lil.Hotel.web.api;

import com.jourdainK.lil.Hotel.data.entity.Reservation;
import com.jourdainK.lil.Hotel.data.repository.ReservationRepository;
import com.jourdainK.lil.Hotel.web.exception.BadRequestException;
import com.jourdainK.lil.Hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    public final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllReservations(){
        return this.reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation reservation){
        return this.reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id){
        Optional<Reservation> reservation = this.reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new NotFoundException("Reservation not found with Id "+ id);
        }

        return reservation.get();
    }


    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation){
        if(id != reservation.getId()){
            throw new BadRequestException("id on path doesn't match body");
        }

        return this.reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteReservation(@PathVariable("id") long id){
        this.reservationRepository.deleteById(id);
    }


    //test on windows curl http://localhost:8080/api/reservations/by-date?reservDate=2023-08-28
    @GetMapping("/by-date")
    public List<Reservation> getAllReservationsByDate(@RequestParam(required = false) String reservDate) {
        if (reservDate != null) {
            try {
                Date date = Date.valueOf(reservDate);
                return this.reservationRepository.findByReservDate(date);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Wrong date format or no reservation found");
            }
        } else {
            throw new RuntimeException("Date parameter is required");
        }
    }

}
