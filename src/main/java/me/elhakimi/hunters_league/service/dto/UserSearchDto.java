package me.elhakimi.hunters_league.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto {
    private UUID id;
    private String email;
    private String username;
    private String cin;
    private String speciesName;
}
