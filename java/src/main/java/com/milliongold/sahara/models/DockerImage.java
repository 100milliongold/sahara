package com.milliongold.sahara.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DockerImage {
    private String image_id;
    private String image_name;
    private String image_tag;
    private Long size;
}
