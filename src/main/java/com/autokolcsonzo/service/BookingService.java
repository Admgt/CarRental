package com.autokolcsonzo.service;

import com.autokolcsonzo.dto.BookingDto;
import com.autokolcsonzo.entity.Booking;
import com.autokolcsonzo.entity.Car;
import com.autokolcsonzo.repository.BookingRepository;
import com.autokolcsonzo.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;

    public BookingService(BookingRepository bookingRepository, CarRepository carRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
    }

    public Booking createBooking(BookingDto bookingDto) {
        Car car = carRepository.findById(bookingDto.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid car ID"));

        List<Car> availableCars = carRepository.findAvailableCars(bookingDto.getStartDate(), bookingDto.getEndDate());
        if (availableCars.stream().noneMatch(c -> c.getId().equals(car.getId()))) {
            throw new IllegalStateException("A kiválasztott autó ebben az időszakban már nem szabad.");
        }

        long numberOfDays = ChronoUnit.DAYS.between(bookingDto.getStartDate(), bookingDto.getEndDate());
        if (numberOfDays <= 0) {
            throw new IllegalArgumentException("A foglalásnak legalább 1 naposnak kell lennie.");
        }

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setCustomerName(bookingDto.getCustomerName());
        booking.setCustomerEmail(bookingDto.getCustomerEmail());
        booking.setCustomerAddress(bookingDto.getCustomerAddress());
        booking.setCustomerPhone(bookingDto.getCustomerPhone());
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());
        booking.setTotalPrice((int) (car.getDailyPrice() * numberOfDays));

        return bookingRepository.save(booking);
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }
}
