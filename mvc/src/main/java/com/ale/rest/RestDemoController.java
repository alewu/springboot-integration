package com.ale.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestDemoController {

    @PostMapping("/test")
    public ResponseEntity<RestDTO> testResponse() {
        RestDTO restDTO = new RestDTO();
        restDTO.setId("123");
        restDTO.setName("jack");
        return ResponseEntity.ok(restDTO);
    }
}
