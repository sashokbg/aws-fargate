package bg.alex.ecs.backen;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RestApi {

    @CrossOrigin(origins = "*")
    @GetMapping("/cars")
    public ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<>();

        cars.add(new Car(1, "Ferrari"));
        cars.add(new Car(2, "Porche"));
        cars.add(new Car(3, "Dodge"));

        return cars;
    }
}
