//package com.examen.core.service;
//
//import com.examen.core.model.Price;
//import com.examen.core.repository.PriceRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class DatabaseInitializer {
//    private final PriceRepository priceRepository;
//
//    @Autowired
//    public DatabaseInitializer(PriceRepository priceRepository) {
//        this.priceRepository = priceRepository;
//    }
//
//    @PostConstruct
//    public void initializeDatabase() {
//        List<Price> prices = new ArrayList<>();
//
//        // Ejemplo de precios
//        prices.add(new Price(1L, getDate(2020, 6, 14, 0, 0, 0), getDate(2020, 12, 31, 23, 59, 59),
//                1, 35455L, 0, new BigDecimal("35.50"), "EUR"));
//        prices.add(new Price(1L, getDate(2020, 6, 14, 15, 0, 0), getDate(2020, 6, 14, 18, 30, 0),
//                2, 35455L, 1, new BigDecimal("25.45"), "EUR"));
//        prices.add(new Price(1L, getDate(2020, 6, 15, 0, 0, 0), getDate(2020, 6, 15, 11, 0, 0),
//                3, 35455L, 1, new BigDecimal("30.50"), "EUR"));
//        prices.add(new Price(1L, getDate(2020, 6, 15, 16, 0, 0), getDate(2020, 12, 31, 23, 59, 59),
//                4, 35455L, 1, new BigDecimal("38.95"), "EUR"));
//
//        priceRepository.saveAll(prices);
//    }
//
//    private Date getDate(int year, int month, int day, int hour, int minute, int second) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month - 1, day, hour, minute, second);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTime();
//    }
//}
