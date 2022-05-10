package renting.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import renting.entity.Car;
import renting.entity.CarBrand;
import renting.entity.RentalAndReturnDate;
import renting.exception.NoAvailableCar;
import renting.repository.CarRepository;
import renting.repository.CarTypeRepository;
import renting.repository.ContactDetailsRepository;
import renting.repository.RentalAndReturnDateRepository;

import java.util.List;
import java.util.Set;

@Controller
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
        return "add-car";
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

    @DeleteMapping(value = "/car/{id}")
    public void deleteCar(@PathVariable Long id){
        carRepository.deleteById(id);
    }

    @GetMapping(value = "/car/delete/{id}")
    public String deleteCar(@PathVariable ("id") Long id, Model model) {
        carRepository.deleteById(id);
        return "redirect:/car/all";
    }

//    @GetMapping(value = "/car/{carBrand}")
//    public String getCarByBrand(@PathVariable("carBrand") CarBrand carBrand, Model model) {
//        Set<Car> cars = carRepository.findAByCarBrandContaining(carBrand);
//        if(cars != null) {
//            model.addAttribute("cars", cars);
//            return "all-cars";
//        }else{
//            return "all-cars";
//        }
//    }
    @RequestMapping(path = {"/","/search"})
    public String findCarByBrand(Car car, Model model, String keyword) {
        if(keyword!=null) {
            List<Car> list = carRepository.findByKeyword(keyword);
            model.addAttribute("list", list);
            return "all-cars";
        }else {
            List<Car> list = carRepository.findAll();
            model.addAttribute("list", list);}
        return "all-cars";
    }

    @GetMapping("/car/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("car", carRepository.getById((id)));
        return "update-car";
    }

    @RequestMapping("/car/update/{id}")
    public String updateCar(@PathVariable("id") Long id, @ModelAttribute Car newCar, Model model){
        Car oldCar = carRepository.getById(id);
        try{
            if (newCar.getCarName() != null){
                oldCar.setCarName(newCar.getCarName());
            }
            if (newCar.getYear() != null){
                oldCar.setYear(newCar.getYear());
            }
            if (newCar.getCarBrand() != null){
                oldCar.setCarBrand(newCar.getCarBrand());
            }
            if (newCar.getCarType() != null){
                oldCar.setCarType(newCar.getCarType());
            }
            if (newCar.getCity() != null){
                oldCar.setCity(newCar.getCity());
            }
            if (newCar.getQuantity() != null) {
                if(newCar.getQuantity() == 0){
                    oldCar.setAvailable(false);
                }else{
                    oldCar.setAvailable(true);
                }
                oldCar.setQuantity(newCar.getQuantity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("car", oldCar);
        carRepository.save(oldCar);
        return "redirect:/car/all";
        }

    @GetMapping(value = "/car/rent/{id}")
    public String rentCarForm(@PathVariable("id") Long id, Model model) {
        Car car = carRepository.getById(id);
        RentalAndReturnDate rentalReturnDate = new RentalAndReturnDate();
        rentalReturnDate.setReturnDate(rentalReturnDate.getRentalDate().plusDays(30));
        int[] quantity = {0, 1, 2, 3};
        for (int i = 0; i < quantity.length; i++) {
            if (car.getQuantity() == quantity[0]) {
                model.addAttribute("message", "No car available!");
            } else if (car.getQuantity() == quantity[1]) {
                model.addAttribute("message1", "Last available car!");
            } else if (car.getQuantity() == quantity[2]) {
                model.addAttribute("message2", "Last 2 available cars!");
            } else if (car.getQuantity() == quantity[3]) {
                model.addAttribute("message3", "Last 3 available cars!");
            }
        }
        model.addAttribute("rentalReturnDate", rentalReturnDate);
        model.addAttribute("car", car);
        return "rent-car";
    }

    @PostMapping(value = "/car/rent/{id}")
    public String rentCar(@PathVariable("id") Long id, @ModelAttribute("rentalReturnDate")
    @RequestBody RentalAndReturnDate rentalReturnDate, RedirectAttributes redirectAttributes) {
        rentalReturnDate.setReturnDate(rentalReturnDate.getRentalDate().plusDays(14));
        rentalAndReturnDateRepository.save(rentalReturnDate);
        Car car = carRepository.getById(id);
        car.setRentalReturnDate(rentalReturnDate);
        try {
            if (car.getQuantity() > 0) {
                car.setQuantity(car.getQuantity() - 1);
            } else {
                throw new NoAvailableCar("There is no car available to rent!");
            }
        } catch (NoAvailableCar cars) {
            System.out.println(cars);
        }
        if(car.getQuantity() == 0){
            car.setAvailable(false);
        }
        carRepository.save(car);
        redirectAttributes.addFlashAttribute("message4", "The car has been rented successfully.");
        return "redirect:/car/all";
    }
    }

