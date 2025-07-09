package com.autokolcsonzo.controller;

import com.autokolcsonzo.dto.BookingDto;
import com.autokolcsonzo.entity.Car;
import com.autokolcsonzo.service.BookingService;
import com.autokolcsonzo.service.CarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class PublicController {

    private final CarService carService;
    private final BookingService bookingService;

    public PublicController(CarService carService, BookingService bookingService) {
        this.carService = carService;
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "public/index";
    }

    @GetMapping("/search")
    public String searchCars(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                             Model model) {
        List<Car> availableCars = carService.getAvailableCars(startDate, endDate);
        model.addAttribute("cars", availableCars);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "public/index";
    }

    @GetMapping("/book/{carId}")
    public String showBookingForm(@PathVariable Long carId,
                                  @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                  Model model) {
        Car car = carService.findCarById(carId).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + carId));
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(carId);
        bookingDto.setStartDate(startDate);
        bookingDto.setEndDate(endDate);

        model.addAttribute("booking", bookingDto);
        model.addAttribute("car", car);
        model.addAttribute("numberOfDays", numberOfDays);
        model.addAttribute("totalPrice", car.getDailyPrice() * numberOfDays);
        return "public/booking-form";
    }

    @PostMapping("/book")
    public String createBooking(@ModelAttribute BookingDto bookingDto) {
        bookingService.createBooking(bookingDto);
        return "redirect:/booking-success";
    }

    @GetMapping("/booking-success")
    public String bookingSuccess() {
        return "public/booking-success";
    }
}