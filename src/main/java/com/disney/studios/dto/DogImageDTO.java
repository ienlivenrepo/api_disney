package com.disney.studios.dto;

import com.disney.studios.entity.DogBreed;
import lombok.Builder;
import lombok.Data;

/**
 * Dog image data transfer object containing details about the dog
 * Created by : Indrajit singh
 *
 */
@Data
@Builder
public class DogImageDTO {
    private Integer image_id;
    private String imageUrl;
    private String imageIdentity;
    private int vote;
    private DogBreed breed;
}
