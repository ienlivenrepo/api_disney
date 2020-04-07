package com.disney.studios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
public class DogImage implements Serializable {

    private static final long serialVersionUID = -6760472404431489436L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="image_id")
    private Integer image_id;
    private String imageUrl;
    private String imageIdentity;
    private int vote;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "breed_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DogBreed breed;
}
