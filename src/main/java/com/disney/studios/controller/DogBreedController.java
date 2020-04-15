package com.disney.studios.controller;

import com.disney.studios.dto.DogBreedDTO;
import com.disney.studios.dto.DogImageDTO;
import com.disney.studios.dto.VoteDetailsDTO;
import com.disney.studios.entity.DogImage;
import com.disney.studios.service.IDogBreedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by : Indrajit singh
 * DogBreedController
 */
@Controller
@Slf4j
public class DogBreedController  implements IDogBreedController{
    IDogBreedService dogBreedService;

    public DogBreedController(IDogBreedService dogBreedService){
        this.dogBreedService=dogBreedService;
    }

    /**
     * Get iamges grouped by breed
     * @return ResponseEntity<List<DogBreedDTO>>
     */
    @Override
    public ResponseEntity<List<DogBreedDTO>> getAllImagesByBreed() {
        return new ResponseEntity<>(dogBreedService.getAllImagesByBreed(), HttpStatus.OK);
    }

    /**
     * Get images by breed name
     * @param breedName
     * @return ResponseEntity<List<DogImage>>
     */
    @Override
    public ResponseEntity<List<DogImage>>  getImagesByBreed(@PathVariable(value = "breedName",required = true) @NotEmpty String breedName)throws Exception {
        return new ResponseEntity<List<DogImage>>(dogBreedService.getImagesByBreed(breedName).getImages(),HttpStatus.OK);
    }

    /**
     * Vote image based in clientID,imageID,vote
     * @param voteDetailsDTO
     * @return ResponseEntity<DogImage>
     * @throws Exception
     */
    @Override
    public ResponseEntity<DogImage> voteImage(@RequestBody VoteDetailsDTO voteDetailsDTO) throws Exception {
        DogImage dogImage=dogBreedService.voteImage(voteDetailsDTO);
        return new ResponseEntity(dogImage,HttpStatus.OK);
    }

    /**
     * Get Image details for a specific ID
     * @param imageID
     * @return ResponseEntity<DogImageDTO>
     * @throws Exception
     */
    @Override
    public ResponseEntity<DogImageDTO> getImageDetails(@PathVariable(value="imageID") Integer imageID) throws Exception {
        return new ResponseEntity<DogImageDTO>(dogBreedService.getImageDetails(imageID),HttpStatus.OK);
    }
}
