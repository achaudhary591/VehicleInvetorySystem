package controller;

import com.lld.automobile.db.entity.Vehicle;
import com.lld.automobile.db.entity.VehicleStation;
import com.lld.automobile.db.repository.VehicleRepository;
import com.lld.automobile.db.repository.VehicleStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usage")
public class UsageController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleStationRepository stationRepository;

    @PostMapping("/return")
    public Vehicle returnVehicle(@RequestParam Long vehicleId, @RequestParam Long stationId) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(String.valueOf(vehicleId)).orElseThrow(() -> new Exception("Vehicle not found"));
        VehicleStation station = stationRepository.findById(String.valueOf(stationId)).orElseThrow(() -> new Exception("Station not found"));
                vehicle.setVehicleStation(station);
        return vehicleRepository.save(vehicle);
    }

}

