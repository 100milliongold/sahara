package com.milliongold.sahara.docker;

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
     */
    @GetMapping
    public ResponseEntity<?> getContainerList() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(docker.getContainerList());
        } catch (DockerException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 컨테이너 생성
     * 
     * @param container
     * @return
     */
    @PutMapping
    public ResponseEntity<?> crateContainer(DockerContainer container) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(docker.crateContainer(container));
        } catch (DockerException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 컨테이너 시작
     * 
     * @param id
     * @return
     */
    @PostMapping("/{id}/start")
    public ResponseEntity<?> startContainer(@PathVariable("id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(docker.startContainer(id));
        } catch (DockerException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 컨테이너 중지
     * 
     * @param id
     * @return
     */
    @PostMapping("/{id}/stop")
    public ResponseEntity<?> stopContainer(@PathVariable("id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(docker.stopContainer(id));
        } catch (DockerException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 컨테이너 삭제
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContainer(@PathVariable("id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(docker.deleteContainer(id));
        } catch (DockerException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
