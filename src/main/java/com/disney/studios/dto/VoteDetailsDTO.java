package com.disney.studios.dto;

import com.disney.studios.constants.VoteEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Vote details data transfer object ")
public class VoteDetailsDTO {
    @ApiModelProperty(notes = "unique client ID who is casting the vote")
    private String clientID;
    @ApiModelProperty(notes = "Voting reference whether it is up or down")
    private VoteEnum vote;
    @ApiModelProperty(notes = "unique id for the image of dog")
    private Integer dogImageID;
}
