package kh.edu.test.FoodCompany.service;

import kh.edu.test.FoodCompany.dto.Snack;
import kh.edu.test.FoodCompany.mapper.SnackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackServiceImpl implements SnackService {
    @Autowired
    private SnackMapper snackMapper;


    @Override
    public void addSnack(Snack snack) {
        snackMapper.insertSnack(snack);
    }

    @Override
    public List<Snack> getAllSnacks() {
        return snackMapper.getAllSnacks();
    }

    @Override
    public Snack getSnackById(int snackId) {
        return snackMapper.getSnackById(snackId);
    }

    @Override
    public void updateSnack(Snack snack) {
        snackMapper.updateSnack(snack);
    }

    @Override
    public void deleteSnack(int snackId) {
        snackMapper.deleteSnack(snackId);
    }
}