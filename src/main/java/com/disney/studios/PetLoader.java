package com.disney.studios;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import com.disney.studios.repository.IDogBreedRepository;
import com.disney.studios.repository.IDogImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.slf4j.LoggerFactory.getLogger;

@Component
@Slf4j
public class PetLoader implements InitializingBean {

    // Resources to the different files we need to load.
    @Value("classpath:data/labrador.txt")
    private Resource labradors;

    @Value("classpath:data/pug.txt")
    private Resource pugs;

    @Value("classpath:data/retriever.txt")
    private Resource retrievers;

    @Value("classpath:data/yorkie.txt")
    private Resource yorkies;

    @Autowired
    DataSource dataSource;

    @Autowired
    IDogBreedRepository dogBreedRepository;

    @Autowired
    IDogImageRepository dogImageRepository;

    /**
     * Load the different breeds into the data source after
     * the application is ready.
     *
     * @throws Exception In case something goes wrong while we load the breeds.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        loadBreed("Labrador", labradors);
        loadBreed("Pug", pugs);
        loadBreed("Retriever", retrievers);
        loadBreed("Yorkie", yorkies);
    }

    /**
     * Reads the list of dogs in a category and (eventually) add
     * them to the data source.
     * @param breed The breed that we are loading.
     * @param source The file holding the breeds.
     * @throws IOException In case things go horribly, horribly wrong.
     */
    private void loadBreed(String breed, Resource source) throws IOException {
        log.info("Loading breed {}", breed);
        DogBreed persistedBreed=dogBreedRepository.save(DogBreed.builder().breedName(breed).build());
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String identity = line.substring(line.length()-11, line.length()-4);
                dogImageRepository.save(DogImage.builder().imageIdentity(identity).imageUrl(line).breed(persistedBreed).vote(0).build());
            }
        }
    }
}
