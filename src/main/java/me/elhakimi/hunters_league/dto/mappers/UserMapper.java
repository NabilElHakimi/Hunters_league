package me.elhakimi.hunters_league.dto.mappers;

import me.elhakimi.hunters_league.domains.User;
import me.elhakimi.hunters_league.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDto(UserDTO dto);

    UserDTO toDto(User user);
}
