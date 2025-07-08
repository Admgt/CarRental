package com.autokolcsonzo.autokolcsonzo.repository;

import com.autokolcsonzo.autokolcsonzo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.active = true AND c.id NOT IN " +
            "(SELECT b.car.id FROM Booking b WHERE b.startDate < :endDate AND b.endDate > :startDate)")
    List<Car> findAvailableCars(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}