package kh.edu.test.FoodCompany.controller;


import kh.edu.test.FoodCompany.dto.Snack;
import kh.edu.test.FoodCompany.service.SnackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snacks")
public class SnackController {
    @Autowired
    private SnackServiceImpl snackService;


    // [POST] /api/snacks - 과자 추가
    @PostMapping
    public void addSnack(@RequestBody Snack snack) {
        snackService.addSnack(snack);
    }

    // [GET] /api/snacks - 모든 과자 조회
    @GetMapping
    public List<Snack> getAllSnacks() {
        return snackService.getAllSnacks();
    }

    // [GET] /api/snacks/{snackId} - 특정 과자 조회
    @GetMapping("/{snackId}")
    public Snack getSnackById(@PathVariable int snackId) {
        return snackService.getSnackById(snackId);
    }

    // [PUT] /api/snacks/{snackId} - 특정 과자 수정
    @PutMapping("/{snackId}")
    public void updateSnack(@PathVariable int snackId, @RequestBody Snack snack) {
        snack.setSnackId(snackId);
        snackService.updateSnack(snack);
    }

    // [DELETE] /api/snacks/{snackId} - 특정 과자 삭제
    @DeleteMapping("/{snackId}")
    public void deleteSnack(@PathVariable int snackId) {
        snackService.deleteSnack(snackId);
    }
}