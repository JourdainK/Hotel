package com.jourdainK.lil.Hotel.web.api;

import com.jourdainK.lil.Hotel.data.entity.Guest;
import com.jourdainK.lil.Hotel.data.repository.GuestRepository;
import com.jourdainK.lil.Hotel.web.exception.BadRequestException;
import com.jourdainK.lil.Hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
    public final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getAllGuests(){
        return this.guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id){
        Optional<Guest> guest = this.guestRepository.findById(id);
        if(guest.isEmpty()){
            throw new NotFoundException("Guest not found with id " + id);
        }

        return guest.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest){
        return this.guestRepository.save(guest);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable("id") long id, @RequestBody Guest guest){
        if(id != guest.getId()){
            throw new BadRequestException("id on path deosn't match body");
        }

        return this.guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable("id") long id){
        this.guestRepository.deleteById(id);
    }

}
