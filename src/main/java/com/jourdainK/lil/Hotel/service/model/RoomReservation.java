package com.jourdainK.lil.Hotel.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;
@Getter
@Setter
@ToString
public class RoomReservation {
    private long roomId;
    private String roomName;
    private String roomNumber;
    private long guestId;
    private String firstName;
    private String lastName;
    private long reservationId;
    private Date reservationDate;
}
