package controller;

import com.lld.automobile.db.entity.Vehicle;
import com.lld.automobile.db.entity.VehicleStation;
import com.lld.automobile.db.repository.UserRepository;
import com.lld.automobile.db.repository.VehicleRepository;
import com.lld.automobile.db.repository.VehicleStationRepository;
import com.lld.automobile.model.VehicleDTO;
import com.lld.automobile.service.VehicleInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VehicleInventoryService vehicleInventoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    private VehicleStationRepository vehicleStationRepository;

    @PostMapping("/addVehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){

        Vehicle vehicleObj = vehicleRepository.save(vehicle);
        return new ResponseEntity<Vehicle>(vehicleObj, HttpStatus.OK);
    }

    @PostMapping("/stations")
    public VehicleStation createStation(@RequestBody VehicleStation station) {
        return vehicleStationRepository.save(station);
    }

    @PostMapping("/inventory")
    public Vehicle addToInventory(@RequestParam Long vehicleId, @RequestParam Long stationId) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(String.valueOf(vehicleId)).orElseThrow(() -> new Exception("Vehicle not found"));
        VehicleStation station = vehicleStationRepository.findById(String.valueOf(stationId)).orElseThrow(() -> new Exception("Station not found"));
        vehicle.setVehicleStation(station);
        return vehicleRepository.save(vehicle);
    }
}
