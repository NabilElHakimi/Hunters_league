package org.rabie.hunters_league.web.vm.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResultsResponseVm {
    private UserResponseVm user;
    private List<CompetitionResults> competitions;
}
