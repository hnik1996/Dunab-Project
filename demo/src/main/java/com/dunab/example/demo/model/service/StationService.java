package com.dunab.example.demo.model.service;

import com.dunab.example.demo.api.dto.StationDTO;
import com.dunab.example.demo.model.entity.Station;
import com.dunab.example.demo.model.repository.CompanyRepository;
import com.dunab.example.demo.model.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.UnknownHostException;
import java.util.Optional;

@Service
@Transactional
public class StationService {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private StationRepository stationRepo;

    public Station add(StationDTO dto) throws UnknownHostException {
        companyRepo.findById(dto.getCompany_id()).orElseThrow(() -> new RuntimeException("No parent company found by id: " + dto.getCompany_id()));
        Station station = new Station();
        station.setName(dto.getName());
        GeoJsonPoint point = new GeoJsonPoint(dto.getLongitude(), dto.getLatitude());
        station.setPoint(point);
        station.setCompany_id(dto.getCompany_id());
        stationRepo.insert(station);
        return station;
    }

    public Boolean delete(String id) {
        Optional<Station> stationOptional = stationRepo.findById(id);
        if (stationOptional.isPresent()) {
            stationRepo.deleteById(id);
            return true;
        } else return false;
    }
}
