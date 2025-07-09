package com.autokolcsonzo.controller;

import com.autokolcsonzo.entity.Car;
import com.autokolcsonzo.service.BookingService;
import com.autokolcsonzo.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CarService carService;
    private final BookingService bookingService;

    public AdminController(CarService carService, BookingService bookingService) {
        this.carService = carService;
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAllBookings());
        return "admin/booking-list";
    }

    @GetMapping("/cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.findAllCars());
        return "admin/car-list";
    }

    @GetMapping("/cars/new")
    public String newCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "admin/car-form";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        Car car = carService.findCarById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("car", car);
        return "admin/car-form";
    }

    @PostMapping("/cars/save")
    public String saveCar(@ModelAttribute Car car) {
        carService.saveCar(car);
        return "redirect:/admin/cars";
    }

    @PostMapping("/cars/deactivate/{id}")
    public String deactivateCar(@PathVariable Long id) {
        carService.deactivateCar(id);
        return "redirect:/admin/cars";
    }
}