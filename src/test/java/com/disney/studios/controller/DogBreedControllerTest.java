package com.disney.studios.controller;

import com.disney.studios.constants.VoteEnum;
import com.disney.studios.dto.DogBreedDTO;
import com.disney.studios.dto.DogImageDTO;
import com.disney.studios.dto.VoteDetailsDTO;
import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import com.disney.studios.service.DogBreedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.disney.studios.constants.VoteEnum.UP;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@RunWith(SpringRunner.class)
@WebMvcTest(DogBreedController.class)
public class DogBreedControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DogBreedService dogBreedService;

    @Test
    public void getAllImagesByBreedTest() throws Exception{
        when(dogBreedService.getAllImagesByBreed()).thenReturn(getDogImageByBreedList());
        mvc.perform(get("/groupImagesByBreed")).andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void getImagesByBreed()throws Exception{
        when(dogBreedService.getImagesByBreed("Labrader")).thenReturn(getDogImageByBreedList().get(0));
        mvc.perform(get("/getImagesByBreed/Labrader")).andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void voteImage() throws Exception{
        DogBreed dogBreed=DogBreed.builder().breedName("Labrador").breed_id(1).build();
        String jsonContent="{ \"clientID\": \"a1234\", \"vote\": \"UP\",\"dogImageID\":1 }";
        when(dogBreedService.voteImage(VoteDetailsDTO.builder().clientID("a1234").dogImageID(1).vote(UP).build())).thenReturn(DogImage.builder().vote(2).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(1).imageIdentity("eE29vX4").build());
        mvc.perform(put("/vote")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonContent)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("http://i.imgur.com/eE29vX4.png"));
    }

    @Test
    public void getImageDetails() throws Exception{
        when(dogBreedService.getImageDetails(1)).thenReturn(getDogImageList().get(0));
        mvc.perform(get("/getImageDetails/1")).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("http://i.imgur.com/eE29vX4.png"));
    }
    private List<DogBreedDTO> getDogImageByBreedList(){
        List<DogImage> dogImageList=new ArrayList<>();
        DogBreed dogBreed=DogBreed.builder().breedName("Labrador").breed_id(1).build();
        dogImageList.add(DogImage.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(1).imageIdentity("eE29vX4").build());
        dogImageList.add(DogImage.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(2).imageIdentity("eE29vX4").build());
        dogImageList.add(DogImage.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(3).imageIdentity("eE29vX4").build());
        dogImageList.add(DogImage.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(4).imageIdentity("eE29vX4").build());
        DogBreedDTO dogBreedDTO=DogBreedDTO.builder().dogBreed(dogBreed).images(dogImageList).build();
        List<DogBreedDTO> dogBreedDTOList=new ArrayList<DogBreedDTO>();
        dogBreedDTOList.add(dogBreedDTO);
        return  dogBreedDTOList;
    }

    private List<DogImageDTO> getDogImageList(){
        DogBreed dogBreed=DogBreed.builder().breedName("Labrador").breed_id(1).build();
        List<DogImageDTO> dogImageList=new ArrayList<>();
        dogImageList.add(DogImageDTO.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(1).imageIdentity("eE29vX4").build());
        dogImageList.add(DogImageDTO.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(2).imageIdentity("eE29vX4").build());
        dogImageList.add(DogImageDTO.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(3).imageIdentity("eE29vX4").build());
        dogImageList.add(DogImageDTO.builder().vote(1).breed(dogBreed).imageUrl("http://i.imgur.com/eE29vX4.png").image_id(4).imageIdentity("eE29vX4").build());
        return dogImageList;
    }
}
