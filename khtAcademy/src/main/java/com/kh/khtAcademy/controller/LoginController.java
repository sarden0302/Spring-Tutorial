package com.kh.khtAcademy.controller;

/*
쿠키와 세션
클라이언트와 서버 간의 상태를 관리하는데 사용하는 중요한 기술

쿠키 (Cookie)
= 사용자의 컴퓨터에 저장되는 작은 데이터 파일
서버가 클라이언트 컴퓨터에 생성하고 저장한 후, 클라이언트의 요청에 따라
저장된 쿠키의 정보를 다시 서버로 전송

    - 서버는 쿠키 데이터를 직접 관리하지 않음

    - 일반적으로 쿠키의 크기는 4KB 로 제한

    - 쿠키는 만료 시간을 지정할 수 있고, 만료시간이 지나면 자동으로 삭제

    - 보안에 취약 / 클라이언트 개인 정보를 저장하지 않는다

    ex) 롤 로그인 : 30일간 유저 정보 저장

    - [용도] : 자동로그인, 테마, 언어 설정

세션 (Session)
= 클라이언트와 서버 간의 상태 정보를 서버 측에서 관리하는 방식 (개발자 측에서 관리)
사용자는 세션 ID를 브라우저의 쿠키나 URL 포함해 서버에 전달
클라이언트 세션 ID만 보유, 실제 데이터는 서버에서 관리

세션은 일정 시간이 지나면 만료 (사용자가 접속을 종료하거나 시간적인 제한 기간 설정)
쿠키보다 상대적으로 안전하며 서버 측에서 관리되므로 민감한 데이터를 다룰 때 적합

[용도]
    : 로그인 상태 관리, 장바구니, 폼 데이터 전송 전달

쿠키 -> 보안의 취약점 -> JWT 토큰 : 스프링, 자바에서 자동으로 관리
*/

import com.kh.khtAcademy.dto.User;
import com.kh.khtAcademy.service.UserProfileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserProfileService userProfileService;

    /*
    @GetMapping("/cookieLogin")
    public String cookieLogin() {
        return "login";
    }
    예를 들어 주소값 /cookieLogin이 있다

    1. @GetMapping("/cookieLogin")  @PostMapping("/cookieLogin")
    주소값에서 처리하는 방식이 다른 경우 동일한 주소값을 사용할 수 있다

    2. @GetMapping("/cookieLogin")  @GetMapping("/cookieLogin")
    @GetMapping 내에서 동일한 주소값이 2개 이상 존재할 경우 똑같은 주소값이
    두가지가 있어 실행 x

    3. @PostMapping("/cookieLogin")  @PostMapping("/cookieLogin")
    @PostMapping 내에서 동일한 주소값이 2개 이상 존재할 경우 똑같은 주소값이
    두가지가 있어 실행 x
     */
    @GetMapping("/cookieLogin")
    public String cookieLogin() {
        return "login";
    }

    // 로그인 성공 시 쿠키(사용자 컴퓨터)에 사용자의 아이디 비밀번호를 저장
    @PostMapping("/cookieLogin")
    public String cookieLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model,
                              HttpSession session,
                              HttpServletResponse response) {
        User user = userProfileService.login(username, password);
        if (user != null) {
            // 세션에 로그인 정보를 저장
            session.setAttribute("loggedInUser", user);

            // 쿠키에 로그인 정보를 저장
            Cookie cookie = new Cookie("loggedInUsername", username);
            cookie.setPath("/"); // 로그인 후 다시 / 접속 시부터 쿠키를 저장
            // cookie.setMaxAge 작성 안할 시 평생 유지
            cookie.setMaxAge(3600); // 60sec x 60 min = 3600 sec
            // cookie.setMaxAge(60 * 60 * 24) // 86400 = 1일 동안 쿠키 정보 유지
            response.addCookie(cookie); // 위와 같은 정보를 저장
            return "redirect:/"; // 로그인한 정보를 가지고 "/"로 이동
        }
        model.addAttribute("error", "아이디 비밀번호가 일치하지 않습니다.");
        return "login";
    }

    // 로그인 상태 유지
    @ModelAttribute //로그인 정보를 "/" 이외 모든 페이지에서 사용할 수 있도록 설정
    public Object addLoggedInUser(HttpSession session,
                                  @CookieValue(value="loggedInUsername", required = false) String username) {
        User user = (User) session.getAttribute("loggedInUser");

        /*
        // 세선에 유저 정보가 없다면 쿠키에서 유저 이름을 가져와 유저 정보가 존재하는지 확인
        if (user == null) {
            user = userProfileService.cookieLogin(loggedInUsername);
        }
        */
        /*
        // 세션에 유저 정보가 있다면 세션에서 로그인 할 수 있도록 설정
        if (user != null) {
            session.setAttribute("loggedInUser", user); // 세션에 로그인 상태가 존재하면 로그인

        }*/

        return user;
    }
}
