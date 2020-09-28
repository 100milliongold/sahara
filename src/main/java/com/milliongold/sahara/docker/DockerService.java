package com.milliongold.sahara.docker;

import java.util.List;

import com.milliongold.sahara.models.DockerContainer;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class DockerService {

    @Autowired
    private DockerClient docker;

    public List<DockerContainer> getContainerList() throws DockerException, InterruptedException {
        List<Container> res = docker.listContainers();
        return res.stream().map(o -> new DockerContainer(o.id(), o.names().get(0), o.imageId())).collect(toList());
    }

}
