package com.dunab.example.demo.api;

import com.dunab.example.demo.api.dto.StationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    @RequestMapping("welcome")
    public ResponseEntity<StationDTO> helloWorld() {

        StationDTO station = new StationDTO();
        station.setId(20L);
        station.setName("someone");
        station.setLatitude("121241.124");
        station.setLongtitude("bingo");
        return ResponseEntity.ok(station);
    }
}
