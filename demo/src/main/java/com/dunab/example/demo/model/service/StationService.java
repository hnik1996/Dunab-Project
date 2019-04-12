package com.dunab.example.demo.model.service;

import com.dunab.example.demo.api.dto.GeoDTO;
import com.dunab.example.demo.api.dto.StationDTO;
import com.dunab.example.demo.core.MongodbConfiguration;
import com.dunab.example.demo.model.entity.Station;
import com.dunab.example.demo.model.repository.CompanyRepository;
import com.dunab.example.demo.model.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StationService {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private StationRepository stationRepo;

    @Autowired
    private MongodbConfiguration configuration;

    public Station add(StationDTO dto) {
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
            stationRepo.delete(stationOptional.get());
            return true;
        } else return false;
    }

    public Station update(StationDTO dto) {
        Optional<Station> stationOptional = stationRepo.findById(dto.getId());
        if (stationOptional.isPresent()) {
            companyRepo.findById(dto.getCompany_id()).orElseThrow(() -> new RuntimeException("No parent company found by id: " + dto.getCompany_id()));
            Station station = stationOptional.get();
            station.setName(dto.getName());
            station.setCompany_id(dto.getCompany_id());
            station.setPoint(new GeoJsonPoint(dto.getLongitude(), dto.getLatitude()));
            stationRepo.save(station);
            return station;
        } else return new Station();
    }

    public Station get(String id) {
        Optional<Station> stationOptional = stationRepo.findById(id);
        return stationOptional.orElseGet(Station::new);
    }

    public List<Station> list() {
        return stationRepo.findAll();
    }

    public GeoResults<Station> findGeo(GeoDTO dto) throws UnknownHostException {
        NearQuery query = NearQuery.near(dto.getLatitude(), dto.getLongitude())
                .maxDistance(dto.getRadios() * 1000)
                .query(Query.query(Criteria.where("company_id").is(dto.getId()))).spherical(true);
        return configuration.mongoTemplate().geoNear(query, Station.class);
    }
}
