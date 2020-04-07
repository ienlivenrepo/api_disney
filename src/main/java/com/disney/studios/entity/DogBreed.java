package com.disney.studios.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * Created by : Indrajit singh
 *
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogBreed implements Serializable {
    private static final long serialVersionUID = 4403150439646967763L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="breed_id")
    private Integer breed_id;
    private String breedName;
}
