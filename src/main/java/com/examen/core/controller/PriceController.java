package com.examen.core.controller;

import com.examen.core.model.Price;
import com.examen.core.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PriceController {
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<Map<String, Object>>getPricesByDateAndProductAndBrand(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                                                         @RequestParam("productId") Long productId,
                                                         @RequestParam("brandId") Long brandId) {
        List<Price> prices = priceService.getPricesByDateAndProductAndBrand(startDate, endDate, productId, brandId);

        Map<String, Object> response = new HashMap<>();
        response.put("prices", prices);

        return ResponseEntity.ok(response);
    }
}
