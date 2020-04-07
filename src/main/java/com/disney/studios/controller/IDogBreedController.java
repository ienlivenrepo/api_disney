package com.disney.studios.controller;

import com.disney.studios.dto.DogBreedDTO;
import com.disney.studios.dto.DogImageDTO;
import com.disney.studios.entity.DogImage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.List;

public interface IDogBreedController {
    @GetMapping("/groupImagesByBreed")
    ResponseEntity<List<DogBreedDTO>> getAllImagesByBreed();

    @GetMapping("/getImagesByBreed/{breedName}")
    ResponseEntity<List<DogImage>> getImagesByBreed(@PathVariable(value = "breedName",required = true)  String breedName)throws Exception;

    @PutMapping("/vote/{vote}/{imageID}")
    ResponseEntity<DogImage> voteImage(@PathVariable(value="vote") String vote, @PathVariable(value="imageID") Integer imageID) throws Exception;

    @GetMapping("/getImageDetails/{imageID}")
    ResponseEntity<DogImageDTO> getImageDetails(@PathVariable(value="imageID") Integer imageID) throws Exception;
}
