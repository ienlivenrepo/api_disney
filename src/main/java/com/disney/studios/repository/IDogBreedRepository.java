package com.disney.studios.repository;

import com.disney.studios.entity.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by: indrajit singh
 * Data Repository corresponding to DogBreed
 */
@Repository
public interface IDogBreedRepository extends JpaRepository<DogBreed,Integer> {
    public DogBreed findByBreedName(String name);
}
