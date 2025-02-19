package com.jourdainK.lil.Hotel.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jourdainK.lil.Hotel.data.entity.Reservation;
import com.jourdainK.lil.Hotel.data.entity.Guest;
import java.util.Optional;
import java.util.List;
import java.sql.Date;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    Optional<Reservation> findById(long id);
    List<Reservation> findByGuest(Guest guest);
    List<Reservation> findByReservDate(Date reservDate);
}
