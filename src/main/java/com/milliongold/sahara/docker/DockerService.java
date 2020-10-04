package com.milliongold.sahara.docker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.milliongold.sahara.models.DockerContainer;
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

}
