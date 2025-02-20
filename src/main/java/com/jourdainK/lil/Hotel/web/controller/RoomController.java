package com.jourdainK.lil.Hotel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jourdainK.lil.Hotel.data.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    
    @GetMapping
    public String getRooms(Model model) {
        model.addAttribute("rooms", this.roomRepository.findAll());
        return "room-list";
    }
    
}
