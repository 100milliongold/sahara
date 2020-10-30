package com.milliongold.sahara.docker.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.milliongold.sahara.models.DockerContainer;
import com.milliongold.sahara.models.DockerImage;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class DockerService {

    @Autowired
    private DockerClient docker;

    public List<DockerContainer> getContainerList() throws DockerException, InterruptedException {
        List<Container> res = docker.listContainers();
        return res.stream().map(o -> new DockerContainer(o.id(), o.names().get(0), o.imageId(), o.image()))
                .collect(toList());
    }

    public String crateContainer(DockerContainer container) throws DockerException, InterruptedException {
        // Bind container ports to host ports
        final String[] ports = { "80", "22" };
        final Map<String, List<PortBinding>> portBindings = new ConcurrentHashMap<>();
        for (String port : ports) {
            List<PortBinding> hostPorts = Collections.synchronizedList(new ArrayList<>());
            hostPorts.add(PortBinding.of("0.0.0.0", port));
            portBindings.put(port, hostPorts);
        }

        // Bind container port 443 to an automatically allocated available host port.
        List<PortBinding> randomPort = new ArrayList<>();
        randomPort.add(PortBinding.randomPort("0.0.0.0"));
        portBindings.put("443", randomPort);

        final HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

        // Create container with exposed ports
        final ContainerConfig containerConfig = ContainerConfig.builder().hostConfig(hostConfig).image("busybox")
                .exposedPorts(ports).cmd("sh", "-c", "while :; do sleep 1; done").build();

        final ContainerCreation creation = docker.createContainer(containerConfig);
        final String id = creation.id();

        return id;
    }

    public String startContainer(String containerId) throws DockerException, InterruptedException {
        docker.startContainer(containerId);
        return containerId;
    }

    public String stopContainer(String containerId) throws DockerException, InterruptedException {
        docker.stopContainer(containerId, 0);
        return containerId;
    }

    public String deleteContainer(String containerId) throws DockerException, InterruptedException {
        docker.removeContainer(containerId);
        return containerId;
    }

    public List<DockerImage> getImageList() throws DockerException, InterruptedException {
        return docker.listImages().stream().flatMap(image -> {
            String image_id = image.id();
            Long size = image.size();
            return image.repoTags().stream().map(tag -> {
                DockerImage res = new DockerImage();
                String[] tags = tag.split(":");

                res.setImage_id(image_id);
                res.setImage_name(tags[0]);
                res.setImage_tag(tags[1]);
                res.setSize(size);
                return res;
            });
        }).collect(Collectors.toList());
    }

}
