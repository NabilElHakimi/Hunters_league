package org.rabie.hunters_league.web.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoricResponseVm {
    private UserResponseVm user;
    private List<PastCompetitionsResponseVm> pastCompetitions;
}
