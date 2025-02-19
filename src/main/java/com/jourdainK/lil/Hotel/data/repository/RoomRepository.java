package com.jourdainK.lil.Hotel.data.repository;

import com.jourdainK.lil.Hotel.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumberIgnoreCase(String roomNumber);
    List<Room> findByBedInfoIgnoreCase(String bedInfo);
}