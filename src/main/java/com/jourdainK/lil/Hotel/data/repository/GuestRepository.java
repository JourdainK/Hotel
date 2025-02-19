package com.jourdainK.lil.Hotel.data.repository;

import com.jourdainK.lil.Hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long>{
    Optional<Guest> findBylastNameIgnoreCase(String lastName);
    List<Guest> findByState(String state);
}
