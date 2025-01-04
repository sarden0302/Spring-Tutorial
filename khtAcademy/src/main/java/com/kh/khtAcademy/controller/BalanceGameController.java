package com.kh.khtAcademy.controller;

import com.kh.khtAcademy.dto.BalanceGameDto;
import com.kh.khtAcademy.service.BalanceGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * BalanceGameController 클래스
 * 밸런스 게임의 시작과 결과 제출 요청을 처리하는 컨트롤러 클래스
 */
@Controller
public class BalanceGameController {

    @Autowired
    private BalanceGameService service;

    /**
     * 밸런스 게임 시작 화면을 렌더링
     * 모든 질문을 그룹화하여 뷰 템플릿(html 파일)에 전달
     * @param model Spring Model 객체를 사용하여 데이터 전달
     * @return balance-game.html 템플릿을 반환
     */
    @GetMapping("/start")
    public String startGame(Model model) {
        Map<Integer, List<BalanceGameDto>> groupedQuestions = service.getGroupedQuestions(); // 질문 데이터를 questionNumber 기준으로 그룹화하여 가져옴
        model.addAttribute("groupedQuestions", groupedQuestions); // "groupedQuestions"에 groupedQuestions 데이터를 추가
        return "balance-game"; // balance-game.html로 이동
    }

    /**
     * 사용자로부터 제출된 답변을 처리하고 결과를 계산하여 결과 화면으로 이동합
     * @param userAnswers 사용자 응답(Map<String, String> 형태)
     *                    - 키: "userAnswer_질문번호" 형식
     *                    - 값: 사용자가 선택한 답변
     * @param model Spring Model 객체를 사용하여 데이터 전달
     * @return result.html 템플릿을 반환
     */
    @PostMapping("/submit")
    public String submitGame(@RequestParam Map<String, String> userAnswers, Model model) {
        Map<String, Integer> result = service.calculateResult(userAnswers);  // 사용자 응답을 기반으로 결과 계산 가져오기
        System.out.println("result ==> " + result);    // 계산된 결과 출력해서 무사히 잘 됐는지 확인
        model.addAttribute("result", result);// 결과 데이터를 "result"라는 변수이름에 추가

        return "result";
    }


}
