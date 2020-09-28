package com.milliongold.sahara.docker;

import com.spotify.docker.client.exceptions.DockerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/docker/container")
public class DockerController {

    @Autowired
    private DockerService docker;

    @GetMapping
    public ResponseEntity<?> getContainerList() throws DockerException, InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(docker.getContainerList());
    }

    @PutMapping
    public ResponseEntity<?> crateContainer() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteContainer() {
        return null;
    }
}
