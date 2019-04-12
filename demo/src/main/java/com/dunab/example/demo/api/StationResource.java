package com.dunab.example.demo.api;

import com.dunab.example.demo.api.dto.StationDTO;
import com.dunab.example.demo.model.entity.Station;
import com.dunab.example.demo.model.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StationResource {
    private final StationService stationService;

    public StationResource(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/station", method = RequestMethod.POST)
    public @ResponseBody
    Station addStation(@RequestBody StationDTO dto) {
        return stationService.add(dto);
    }

    @RequestMapping(value = "/station/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Boolean deleteStation(@PathVariable String id) {
        return stationService.delete(id);
    }

    @RequestMapping(value = "/station", method = RequestMethod.PUT)
    public @ResponseBody
    Station update(@RequestBody StationDTO dto) {
        return stationService.update(dto);
    }

    @RequestMapping(value = "/station/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Station get(@PathVariable String id) {
        return stationService.get(id);
    }

    @RequestMapping(value = "/station", method = RequestMethod.GET)
    public @ResponseBody
    List<Station> list() {
        return stationService.list();
    }
}
