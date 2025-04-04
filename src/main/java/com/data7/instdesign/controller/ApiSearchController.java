package com.data7.instdesign.controller;

import com.data7.instdesign.dto.ApiResponse;
import com.data7.instdesign.dto.auth.*;
import com.data7.instdesign.service.AuthService;
import com.data7.instdesign.service.SearchService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/search/api")
public class ApiSearchController {

    private final SearchService searchService;

    @PostMapping("/subject")
    public ResponseEntity<ApiResponse<List<String>>> getSubject(@RequestBody Map<String, String> request) {
        try{
            String gradeCode = request.get("gradeCode");
            List<String> subjectList = searchService.getSubject(gradeCode);
            if(!subjectList.isEmpty()){
                return ResponseEntity.ok(ApiResponse.ok(subjectList));
            } else {
                return ResponseEntity.badRequest().body(ApiResponse.fail("과목 출력 실패. 다시 시도해주세요."));
            }
        } catch (Exception e){
            log.error("과목 출력 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.fail("서버 내부 오류가 발생했습니다"));
        }
    }



}