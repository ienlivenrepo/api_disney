package com.disney.studios.repository;

import com.disney.studios.entity.DogBreed;
import com.disney.studios.entity.DogImage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by: indrajit singh
 * Data Repository corresponding to DogImage
 */
@Repository
public interface IDogImageRepository extends JpaRepository<DogImage,Integer> {

    public List<DogImage> findByBreed(DogBreed breed, Sort sortByVotes);
}
