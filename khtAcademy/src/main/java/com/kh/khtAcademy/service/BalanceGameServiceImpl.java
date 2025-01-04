package com.kh.khtAcademy.service;

import com.kh.khtAcademy.dto.BalanceGameDto;
import com.kh.khtAcademy.mapper.BalanceGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BalanceGameServiceImpl 클래스
 * 밸런스 게임의 질문을 관리하고 사용자의 답변을 기반으로 결과를 계산하는 서비스
 */
@Service
public class BalanceGameServiceImpl implements BalanceGameService {

    @Autowired
    private BalanceGameMapper mapper;

    /**
     * 질문을 questionNumber 기준으로 questionNumber 가 동일한 번호 별로 그룹화하여 반환
     * @return 질문 번호(questionNumber)를 키로 하고, 해당 질문 리스트를 값으로 갖는 Map 객체
     */
    @Override
    public Map<Integer, List<BalanceGameDto>> getGroupedQuestions() {
        // 모든 질문을 데이터베이스에서 조회
        List<BalanceGameDto> questions = mapper.selectAllQuestions();

        // questionNumber 기준으로 질문을 그룹화하여 반환
        return questions.stream()
                .collect(Collectors.groupingBy(BalanceGameDto::getQuestionNumber));
    }

    /**
     * 사용자의 답변을 기반으로 점수를 계산
     * @param userAnswers 사용자로부터 전달된 답변(Map<String, String> 형태)
     *                    - 키: "userAnswer_질문번호" 형식 (예: "userAnswer_1")
     *                    - 값: 사용자가 선택한 답변
     * @return 점수 계산 결과(Map<String, Integer> 형태)
     *         - "S": 학업 점수
     *         - "C": 건강 점수
     *         - "H": 연애운 점수
     *         - "D": 금전 점수
     */
    @Override
    public Map<String, Integer> calculateResult(Map<String, String> userAnswers) {
        // 초기 점수 설정
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S", 0); // 학업 점수
        scores.put("C", 0); // 건강 점수
        scores.put("H", 0); // 연애운 점수
        scores.put("D", 0); // 금전 점수

        // 모든 질문을 데이터베이스에서 조회
        List<BalanceGameDto> questions = mapper.selectAllQuestions();

        // 사용자 답변 기반으로 점수 계산
        for (Map.Entry<String, String> entry : userAnswers.entrySet()) {
            // "userAnswer_1" 형식의 키에서 질문 번호를 추출
            int questionNumber = Integer.parseInt(entry.getKey().replace("userAnswer_", ""));

            // 사용자가 선택한 답변
            String answer = entry.getValue();

            // 질문 번호에 해당하는 질문 찾기
            BalanceGameDto selectedQuestion = questions.stream()
                    .filter(q -> q.getQuestionNumber() == questionNumber)
                    .findFirst()
                    .orElse(null); // 질문이 없으면 null 반환

            if (selectedQuestion != null) {
                // 점수 계산 (사용자가 선택한 답변에 따라 점수를 누적)
                scores.put("S", scores.get("S") + selectedQuestion.getScoreS());
                scores.put("C", scores.get("C") + selectedQuestion.getScoreC());
                scores.put("H", scores.get("H") + selectedQuestion.getScoreH());
                scores.put("D", scores.get("D") + selectedQuestion.getScoreD());
            }
        }

        // 계산된 점수를 반환
        return scores;
    }
}