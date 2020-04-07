package com.disney.studios.dto;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 *
 * Created by: indrajit singh
 */
@Data
@Builder
public class DogBreedDTO {
    private DogBreed dogBreed;
    private List<DogImage> images;
}
