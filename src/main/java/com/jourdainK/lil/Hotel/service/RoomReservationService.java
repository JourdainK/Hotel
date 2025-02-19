package com.jourdainK.lil.Hotel.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jourdainK.lil.Hotel.data.repository.GuestRepository;
import com.jourdainK.lil.Hotel.data.repository.ReservationRepository;
import com.jourdainK.lil.Hotel.data.repository.RoomRepository;
import com.jourdainK.lil.Hotel.service.model.RoomReservation;
import com.jourdainK.lil.Hotel.data.entity.Reservation;
import com.jourdainK.lil.Hotel.data.entity.Room;
import com.jourdainK.lil.Hotel.data.entity.Guest;

import io.micrometer.common.util.StringUtils;

@Service
public class RoomReservationService {
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public RoomReservationService(GuestRepository guestRepository, RoomRepository roomRepository,
            ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String reservationDate){
        Date date = null;

        if(StringUtils.isNotEmpty(reservationDate)){
            date = Date.valueOf(reservationDate);
        }else{
            date = new Date(new java.util.Date().getTime());
        }

        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        List<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(roomReservation.getRoomId(), roomReservation);
        });
        List<Reservation> reservations = this.reservationRepository.findByReservDate(date);

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoom().getId());
            roomReservation.setReservationId(reservation.getId());
            roomReservation.setReservationDate(reservation.getReservDate());
            Optional<Guest> guest = this.guestRepository.findById(reservation.getGuest().getId());
            roomReservation.setGuestId(guest.get().getId());
            roomReservation.setFirstName(guest.get().getFirstName());
            roomReservation.setLastName(guest.get().getLastName());

        });

        return roomReservations.values().stream().toList();
    }

    

}
