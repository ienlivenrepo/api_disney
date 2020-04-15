package com.disney.studios.dto;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * Data transfer object to contain dog breed and list of dog images
 * Created by: indrajit singh
 */
@Data
@Builder
public class DogBreedDTO {
    private DogBreed dogBreed;
    private List<DogImage> images;
}
