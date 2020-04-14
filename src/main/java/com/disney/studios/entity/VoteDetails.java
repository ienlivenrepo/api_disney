package com.disney.studios.entity;

import com.disney.studios.constants.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDetails implements Serializable {
    private static final long serialVersionUID = 2845161261018538527L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer votingID;
    private String clientID;
    @Enumerated
    private VoteEnum vote;
    private Integer dogImageID;
}
