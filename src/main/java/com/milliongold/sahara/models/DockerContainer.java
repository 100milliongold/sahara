package com.milliongold.sahara.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DockerContainer {
    private String contaier_id;
    private String container_name;
    private String image_id;

}
