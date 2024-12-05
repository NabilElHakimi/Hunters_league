package me.elhakimi.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import me.elhakimi.hunters_league.domain.Species;
import me.elhakimi.hunters_league.web.vm.request.CreateSpeciesVm;
import me.elhakimi.hunters_league.web.vm.response.SpecieResponseVm;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {
    SpecieResponseVm toListSpeciesVm(Species species);
    Species toSpecies(SpecieResponseVm speciesVm);

    CreateSpeciesVm toSpeciesCreateVm(Species species);
    Species toSpeciesFromCreate(CreateSpeciesVm speciesVm);

}
