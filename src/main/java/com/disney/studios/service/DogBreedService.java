package com.disney.studios.service;

import com.disney.studios.constants.VoteEnum;
import com.disney.studios.dto.DogBreedDTO;
import com.disney.studios.dto.DogImageDTO;
import com.disney.studios.dto.VoteDetailsDTO;
import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import com.disney.studios.entity.VoteDetails;
import com.disney.studios.repository.IDogBreedRepository;
import com.disney.studios.repository.IDogImageRepository;
import com.disney.studios.repository.IVoteImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for Dog breed and images related
 * Created by: Indrjit singh
 */
@Service
public class DogBreedService implements IDogBreedService {
    IDogBreedRepository dogBreedRepository;
    IDogImageRepository dogImageRepository;
    IVoteImageRepository voteImageRepository;

    @Autowired
    public DogBreedService(IDogBreedRepository dogBreedRepository,IDogImageRepository dogImageRepository,IVoteImageRepository voteImageRepository){
        this.dogBreedRepository=dogBreedRepository;
        this.dogImageRepository=dogImageRepository;
        this.voteImageRepository=voteImageRepository;
    }

    /**
     * Get all images grouped by breed
     * @return List<DogBreedDTO>
     */
    @Override
    public List<DogBreedDTO> getAllImagesByBreed() {
       List<DogBreed> dogBreeds=dogBreedRepository.findAll();
       List<DogBreedDTO> dogBreedImages=dogBreeds.stream().map(dogBreed ->
           DogBreedDTO.builder().dogBreed(dogBreed).images(dogImageRepository.findByBreed(dogBreed, Sort.by("vote").descending())).build()) // sort by vote descending
       .collect(Collectors.toList());
       return dogBreedImages;
    }

    /**
     *
     * @param breedName
     * @return DogBreedDTO
     * @throws Exception
     */
    @Override
    public DogBreedDTO getImagesByBreed(String breedName)throws Exception{
        try{
            DogBreed breed=dogBreedRepository.findByBreedName(breedName);
            if(breed!=null) {
                DogBreedDTO dogBreedDTO = DogBreedDTO.builder().dogBreed(breed).images(dogImageRepository.findByBreed(breed, Sort.by("vote").descending())).build(); // sort by vote descending
                return dogBreedDTO;
            }else{
                throw new EntityNotFoundException("No breed was found with the given name");
            }
        }catch(EntityNotFoundException entityException){
            throw entityException;
        }catch(Exception ex){
            throw ex;
        }
    }

    /**
     * Service Method to capture voting details
     * @param voteDetails
     * @return DogImage
     * @throws Exception
     */
    @Transactional
    @Override
    public DogImage voteImage(VoteDetailsDTO voteDetails)throws Exception {
            try {
                VoteDetails voteDetail=voteImageRepository.findByClientID(voteDetails.getClientID());
                if(voteDetail==null) {
                    Optional<DogImage> image = dogImageRepository.findById(voteDetails.getDogImageID());
                    String vote=voteDetails.getVote().name();
                    if (image.isPresent() && voteDetails.getVote() != null) {
                        DogImage dogImage = image.get();
                        Integer voteCount = dogImage.getVote();
                        if (VoteEnum.UP.name().equalsIgnoreCase(vote)) {
                            dogImage.setVote(voteCount + 1);
                            dogImageRepository.save(dogImage);
                        } else if (VoteEnum.DOWN.name().equalsIgnoreCase(vote) && voteCount != 0) {
                            dogImage.setVote(voteCount - 1);
                        } else {
                            throw new Exception("Invalid vote parameter");
                        }
                        DogImage updatedDogImage = dogImageRepository.save(dogImage);
                        voteImageRepository.save(VoteDetails.builder().clientID(voteDetails.getClientID()).dogImageID(voteDetails.getDogImageID()).build());
                        return updatedDogImage;
                    } else {
                        throw new EntityNotFoundException("Image does not exist for which vote is being casted");
                    }
                }else{
                    throw new Exception("The client has already voted");
                }
            }catch(EntityNotFoundException entityException){
                throw entityException;
        }catch(Exception ex){
                throw ex;
            }

    }

    /**
     * Service method to get image details based on imageID
     * @param imageID
     * @return DogImageDTO
     * @throws Exception
     */
    @Override
    public DogImageDTO getImageDetails(Integer imageID) throws Exception {
        try {
            Optional<DogImage> image = dogImageRepository.findById(imageID);
            if (image.isPresent()) {
                DogImage dogImage=image.get();
                return DogImageDTO.builder().breed(dogImage.getBreed())
                        .image_id(dogImage.getImage_id())
                        .vote(dogImage.getVote())
                        .imageIdentity(dogImage.getImageIdentity())
                        .imageUrl(dogImage.getImageUrl()).build();
            }else{
                throw new EntityNotFoundException("No details found for given image");
            }
        }catch(EntityNotFoundException ex){
            throw ex;
        }
        catch(Exception ex){
            throw ex;
        }
    }
}
