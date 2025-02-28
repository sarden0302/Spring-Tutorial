package kh.edu.test.FoodCompany.controller;

import kh.edu.test.FoodCompany.dto.Drink;
import kh.edu.test.FoodCompany.service.DrinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {
    @Autowired
    private DrinkServiceImpl drinkService;


    // [POST] /api/drinks - 음료 추가
    @PostMapping
    public void addDrink(@RequestBody Drink drink) {
        drinkService.addDrink(drink);
    }

    // [GET] /api/drinks - 모든 음료 조회
    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    // [GET] /api/drinks/{drinkId} - 특정 음료 조회
    @GetMapping("/{drinkId}")
    public Drink getDrinkById(@PathVariable int drinkId) {
        return drinkService.getDrinkById(drinkId);
    }

    // [PUT] /api/drinks/{drinkId} - 특정 음료 수정
    @PutMapping("/{drinkId}")
    public void updateDrink(@PathVariable int drinkId, @RequestBody Drink drink) {
        drink.setDrinkId(drinkId);
        drinkService.updateDrink(drink);
    }

    // [DELETE] /api/drinks/{drinkId} - 특정 음료 삭제
    @DeleteMapping("/{drinkId}")
    public void deleteDrink(@PathVariable int drinkId) {
        drinkService.deleteDrink(drinkId);
    }
}