package renting.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import renting.entity.Car;
import renting.entity.RentalAndReturnDate;
import renting.repository.CarRepository;
import renting.repository.ContactDetailsRepository;
import renting.repository.RentalAndReturnDateRepository;


public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private RentalAndReturnDateRepository rentalAndReturnDateRepository;

    @GetMapping(value = "/car/all")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "all-cars";
    }

    @GetMapping(value = "/car/save")
    public String saveCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "add-cars";
    }

    @PostMapping(value = "/car/save")
    public String saveCar(@ModelAttribute("car") @RequestBody Car car, RedirectAttributes redirectAttributes) {
        if(car.getQuantity() > 0){
            car.setAvailable(true);
        }else if(car.getQuantity() == 0){
            car.setAvailable(false);
        }
        carRepository.save(car);
        redirectAttributes.addFlashAttribute("message", "The car has been saved successfully.");
        return "redirect:/car/save";
    }
}
