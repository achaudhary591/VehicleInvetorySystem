package controller;

import com.lld.automobile.db.entity.Vehicle;
import com.lld.automobile.db.entity.VehicleStation;
import com.lld.automobile.db.repository.VehicleStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private VehicleStationRepository vehicleStationRepository;

    @GetMapping("/{id}/vehicles")
    public List<Vehicle> getAvailableVehicles(@PathVariable Long id) throws Exception {
        VehicleStation station = vehicleStationRepository.findById(String.valueOf(id)).orElseThrow(() -> new Exception("Station not found"));
        return station.getVehicles().stream().filter(v -> v.getVehicleStation() == null).collect(Collectors.toList());
    }
}
