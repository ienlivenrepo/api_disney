package com.disney.studios.dto;

import com.disney.studios.entity.DogBreed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Dog image data transfer object containing details about the dog
 * Created by : Indrajit singh
 *
 */
@Data
@Builder
@ApiModel(description = "Dog Image data transfer object ")
public class DogImageDTO {
    @ApiModelProperty(notes = "unique image id")
    private Integer image_id;
    @ApiModelProperty(notes = "image url")
    private String imageUrl;
    @ApiModelProperty(notes = "image identity")
    private String imageIdentity;
    @ApiModelProperty(notes = "vote count")
    private int vote;
    @ApiModelProperty(notes = "reference to dog breed")
    private DogBreed breed;
}
