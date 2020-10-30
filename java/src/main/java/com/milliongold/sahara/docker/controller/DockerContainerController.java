package com.milliongold.sahara.docker.controller;

import com.milliongold.sahara.docker.service.DockerService;
import com.milliongold.sahara.models.DockerContainer;
import com.spotify.docker.client.exceptions.DockerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/docker/container")
public class DockerContainerController {

    @Autowired
    private DockerService docker;

    /**
     * 컨테이너 리스트
     * 
     * @return
     * @throws InterruptedException 
     * @throws DockerException 
     */
    @GetMapping
    public ResponseEntity<?> getContainerList() throws DockerException, InterruptedException {
    	return ResponseEntity.status(HttpStatus.OK).body(docker.getContainerList());
    }

    /**
     * 컨테이너 생성
     * 
     * @param container
     * @return
     * @throws InterruptedException 
     * @throws DockerException 
     */
    @PutMapping
    public ResponseEntity<?> crateContainer(DockerContainer container) throws DockerException, InterruptedException {
    	return ResponseEntity.status(HttpStatus.OK).body(docker.crateContainer(container));
    }

    /**
     * 컨테이너 시작
     * 
     * @param id
     * @return
     * @throws InterruptedException 
     * @throws DockerException 
     */
    @PostMapping("/{id}/start")
    public ResponseEntity<?> startContainer(@PathVariable("id") String id) throws DockerException, InterruptedException {
    	return ResponseEntity.status(HttpStatus.OK).body(docker.startContainer(id));
    }

    /**
     * 컨테이너 중지
     * 
     * @param id
     * @return
     * @throws InterruptedException 
     * @throws DockerException 
     */
    @PostMapping("/{id}/stop")
    public ResponseEntity<?> stopContainer(@PathVariable("id") String id) throws DockerException, InterruptedException {
    	return ResponseEntity.status(HttpStatus.OK).body(docker.stopContainer(id));
    }

    /**
     * 컨테이너 삭제
     * 
     * @param id
     * @return
     * @throws InterruptedException 
     * @throws DockerException 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContainer(@PathVariable("id") String id) throws DockerException, InterruptedException {
    	return ResponseEntity.status(HttpStatus.OK).body(docker.deleteContainer(id));
    }
}
