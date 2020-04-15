package com.disney.studios.controller;

import com.disney.studios.dto.DogBreedDTO;
import com.disney.studios.dto.DogImageDTO;
import com.disney.studios.dto.VoteDetailsDTO;
import com.disney.studios.entity.DogImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Api(value="Dog images management api ")
public interface IDogBreedController {
    @GetMapping("/groupImagesByBreed")
    @ApiOperation(value = "Get all dog images grouped by breed sorted by vote", response = ResponseEntity.class)
    ResponseEntity<List<DogBreedDTO>> getAllImagesByBreed();

    @GetMapping("/getImagesByBreed/{breedName}")
    @ApiOperation(value = "Get dog images by breed name sorted by vote", response = ResponseEntity.class)
    ResponseEntity<List<DogImage>> getImagesByBreed( @PathVariable(value = "breedName",required = true)  String breedName)throws Exception;

    @PutMapping("/vote")
    @ApiOperation(value = "Vote dog images", response = ResponseEntity.class)
    ResponseEntity<DogImage> voteImage(@RequestBody VoteDetailsDTO voteDetails) throws Exception;

    @GetMapping("/getImageDetails/{imageID}")
    @ApiOperation(value = "Get image details by imageID", response = ResponseEntity.class)
    ResponseEntity<DogImageDTO> getImageDetails(@PathVariable(value="imageID") Integer imageID) throws Exception;
}
