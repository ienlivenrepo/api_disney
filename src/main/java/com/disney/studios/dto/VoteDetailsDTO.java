package com.disney.studios.dto;

import com.disney.studios.constants.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteDetailsDTO {
    private String clientID;
    private VoteEnum vote;
    private Integer dogImageID;
}
