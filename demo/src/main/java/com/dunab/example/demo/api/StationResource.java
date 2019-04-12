package com.dunab.example.demo.api;

import com.dunab.example.demo.api.dto.StationDTO;
import com.dunab.example.demo.model.entity.Station;
import com.dunab.example.demo.model.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class StationResource {
    private final StationService stationService;

    public StationResource(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/station", method = RequestMethod.POST)
    public @ResponseBody
    Station addStation(@RequestBody StationDTO dto) throws UnknownHostException {
        return stationService.add(dto);
    }

    @RequestMapping(value = "/station/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Boolean deleteStation(@PathVariable String id) throws UnknownHostException {
        return stationService.delete(id);
    }
}
