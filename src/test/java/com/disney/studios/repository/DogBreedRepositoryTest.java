package com.disney.studios.repository;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DogBreedRepositoryTest {
    @Autowired
    IDogImageRepository dogImageRepository;
    @Autowired
    IDogBreedRepository dogBreedRepository;

    @Value("classpath:data/labrador.txt")
    private Resource labradors;

    @Value("classpath:data/pug.txt")
    private Resource pugs;

    @Value("classpath:data/retriever.txt")
    private Resource retrievers;

    @Value("classpath:data/yorkie.txt")
    private Resource yorkies;

    private int breedCount=0;
    private Integer imageCount=0;
    private HashMap<String,Integer> breedImageCount;

    @Before
    public void setup() throws Exception{
        breedImageCount=new HashMap<>();
       loadBreed("Labrador", labradors);
       loadBreed("Pug", pugs);
       loadBreed("Retriever", retrievers);
       loadBreed("Yorkie", yorkies);
   }



    private void loadBreed(String breed, Resource source) throws IOException {
        breedCount++;
        DogBreed persistedBreed=dogBreedRepository.save(DogBreed.builder().breedName(breed).build());
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String identity = line.substring(line.length()-11, line.length()-4);
                dogImageRepository.save(DogImage.builder().imageIdentity(identity).imageUrl(line).breed(persistedBreed).vote(0).build());
                imageCount++;
            }

        }
        breedImageCount.put(breed,imageCount);
    }
    @Test
    public void findByBreedName()throws Exception{
        Iterable<DogBreed> breedList=dogBreedRepository.findAll();
        assertThat(breedList).hasSize(breedCount);
    }

    @Test
    public void findImagesByBreed()throws Exception{
        DogBreed dogBreed=dogBreedRepository.findByBreedName("Labrador");
        List<DogImage> dogImageList=dogImageRepository.findByBreed(dogBreed, Sort.by("vote").descending());
        assertThat(dogImageList).hasSize(breedImageCount.get("Labrador"));
    }

    @After
    public void refreshData(){
        dogImageRepository.deleteAll();
        dogBreedRepository.deleteAll();
    }
}
