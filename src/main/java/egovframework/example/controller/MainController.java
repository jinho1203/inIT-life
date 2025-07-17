package egovframework.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @GetMapping("/main")
    public String showMain(Model model) {
        // 샘플 데이터
        return "main";  // templates/main.html
    }

    // DTO 클래스 (내부 or 외부 파일로 정의해도 됨)
    static class Project {
        private String id;
        private String name;

        public Project(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}