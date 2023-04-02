package controller;

import com.lld.automobile.db.entity.User;
import com.lld.automobile.db.repository.UserRepository;
import com.lld.automobile.db.repository.VehicleRepository;
import com.lld.automobile.service.VehicleInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class VehicleInventoryController {

    @Autowired
    private VehicleInventoryService vehicleInventoryService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

}
