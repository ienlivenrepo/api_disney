package com.disney.studios.dto;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * Data transfer object to contain dog breed and list of dog images
 * Created by: indrajit singh
 */
@Data
@Builder
@ApiModel(description = "Dog Breed data transfer object ")
public class DogBreedDTO {
    @ApiModelProperty(notes = "Reference to dog breed entity")
    private DogBreed dogBreed;
    @ApiModelProperty(notes = "Reference to the list of dog images")
    private List<DogImage> images;
}
