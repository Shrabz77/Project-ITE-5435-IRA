package com.shrabani.eurekaservice.controller;

import com.shrabani.eurekaservice.model.Market;
import com.shrabani.eurekaservice.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MarketController {

    @Autowired
    private MarketRepository marketRepository;

    // REST endpoint for placing an order (POST via JSON)
    @PostMapping("/api/market/placeOrder")
    @ResponseBody
    public String placeOrder(@RequestBody Market market) {
        marketRepository.save(market);
        return "Order placed for " + market.getStockName();
    }

    // Show Add Stock Form (GET)
    @GetMapping("/stocks/add")
    public String showAddStockForm(Model model) {
        model.addAttribute("market", new Market()); // Prepare empty form data
        return "add-stock"; // maps to templates/add-stock.html
    }

    // Handle Add Stock Form Submission (POST via form)
    @PostMapping("/stocks/add")
    public String addStock(@ModelAttribute("market") Market market, Model model) {
        marketRepository.save(market);
        return "redirect:/stocks"; // redirect to stock list
    }

    // Health check
    @GetMapping("/api/market/health")
    @ResponseBody
    public String health() {
        return "Market Service is running";
    }

    // Show stock list (Thymeleaf view)
    @GetMapping("/stocks")
    public String showStocks(Model model) {
        List<Market> stocks = marketRepository.findAll();
        model.addAttribute("stocks", stocks);
        return "stock-list"; // maps to templates/stock-list.html
    }
}
