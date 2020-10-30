package com.milliongold.sahara.docker.controller;

import com.milliongold.sahara.docker.service.DockerService;
import com.spotify.docker.client.exceptions.DockerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/docker/image")
public class DockerImageController {
    @Autowired
    private DockerService docker;

    /**
     * 이미지 리스트
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getImageList() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(docker.getImageList());
        } catch (DockerException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
