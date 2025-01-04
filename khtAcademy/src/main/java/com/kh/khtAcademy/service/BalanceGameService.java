package com.kh.khtAcademy.service;

import com.kh.khtAcademy.dto.BalanceGameDto;

import java.util.List;
import java.util.Map;

public interface BalanceGameService {
    Map<Integer, List<BalanceGameDto>> getGroupedQuestions();
    Map<String, Integer> calculateResult(Map<String, String> userAnswers);
}