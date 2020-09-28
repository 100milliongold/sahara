package com.milliongold.sahara.config;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerAPI {

    public DockerClient dockerClient;

    @Bean(destroyMethod = "close")
    public DockerClient initDocker() {
        dockerClient = new DefaultDockerClient("unix:///var/run/docker.sock");
        return dockerClient;
    }
}
