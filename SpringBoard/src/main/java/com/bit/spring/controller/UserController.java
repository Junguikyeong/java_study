package com.bit.spring.controller;

import com.bit.spring.model.UserDTO;
import com.bit.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

//    의존성 필드주입
//    @Autowired
//    UserService userService;

    //의존성 생성자주입
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //로그인
    @PostMapping("auth")
    public String auth(HttpSession session, Model model, UserDTO attempt) {
        UserDTO result = userService.auth(attempt);

        if (result != null) {
            session.setAttribute("logIn", result);
            System.out.println("login SUCCESS");
            return "redirect:/board/showAll/1";
        } else {
            model.addAttribute("message", "로그인 정보를 다시 확인해주세요.");
            return "index";
        }
    }

    //회원가입
    @GetMapping("register")
    public String showRegister() {
        return "user/register";
    }

    @PostMapping("register")
    public String register(UserDTO attempt, Model model) {
        if (userService.register(attempt)) {
            model.addAttribute("message", "회원가입성공! 로그인하세요");
            return "redirect:/";
        } else {
            model.addAttribute("message", "중복된 아이디로 가입할 수 없습니다. 다시 입력해주세요");
            return "user/register";
        }
    }
//
//    @GetMapping("update/{id}")
//    public String showUpdate(HttpSession session, Model model, RedirectAttributes redirectAttributes, @PathVariable int id) {
//        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
//
//        if(logIn == null){
//            redirectAttributes.addFlashAttribute("message","다시로그인해주세여.");
//            return "redirect:/";
//        }
//
//        UserDTO u = userService.selectOne(id);
//        if(u==null || u.getId() != logIn.getId()){
//            redirectAttributes.addFlashAttribute("message","유효하지 않은 접근입니다.");
//            return "redirect:/";
//        }
//
//        model.addAttribute("user", u);
//        return "/user/update";
//    }
//
//
//    //회원정보수정
//    @PostMapping("update")
//    public String update(HttpSession session, Model model, UserDTO userDTO) {
//        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            model.addAttribute("message", "다시로그인해주세여.");
//            return "redirect:/";
//        }
//
//        UserDTO origin = userService.selectOne(logIn.getId());
//
//        if(origin == null){
//            model.addAttribute("message", "유효하지 않은 접근입니다.");
//            return "redirect:/";
//        }
//
//        origin.setUsername(userDTO.getUsername());
//        origin.setPassword(userDTO.getPassword());
//        origin.setNickname(userDTO.getNickname());
//
//        userService.update(origin);
//
//        return "redirect:/board/showAll/1";
//    }
//
//    @GetMapping("delete/{id}")
//    public String deleteUser(HttpSession session, RedirectAttributes redirectAttributes,@PathVariable int id){
//        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            redirectAttributes.addFlashAttribute("message","다시로그인해주세여.");
//            return "redirect:/";
//        }
//
//        UserDTO u = userService.selectOne(id);
//        if(u==null || u.getId() != logIn.getId()){
//            redirectAttributes.addFlashAttribute("message","유효하지 않은 접근입니다.");
//            return "redirect:/board/showAll/1";
//        }
//
//        userService.delete(id);
//
//        return "redirect:/";
//    }


}
