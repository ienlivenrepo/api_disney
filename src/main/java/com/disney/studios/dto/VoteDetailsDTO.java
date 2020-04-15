package com.disney.studios.dto;

import com.disney.studios.constants.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VoteDetails data transfer object containing details about Vote casted
 * clientID- unique client id
 * vote- Enum containing constant voting values
 * dogImageID - unique dog image ID
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteDetailsDTO {
    private String clientID;
    private VoteEnum vote;
    private Integer dogImageID;
}
