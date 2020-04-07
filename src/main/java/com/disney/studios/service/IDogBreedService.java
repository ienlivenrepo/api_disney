package com.disney.studios.service;

import com.disney.studios.dto.DogBreedDTO;
import com.disney.studios.dto.DogImageDTO;
import com.disney.studios.entity.DogImage;
import java.util.List;

/**
 * Interface containing contract for service
 * Created by:Indrajit singh
 */
public interface IDogBreedService {
    public List<DogBreedDTO> getAllImagesByBreed();
    public DogBreedDTO getImagesByBreed(String breedName)throws Exception;
    public DogImage voteImage(String vote, Integer imageID) throws Exception;
    public DogImageDTO getImageDetails(Integer imageID)throws Exception;
}
