package me.elhakimi.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import me.elhakimi.hunters_league.domain.AppUser;

import me.elhakimi.hunters_league.web.vm.request.CreatNewUserVm;
import me.elhakimi.hunters_league.web.vm.request.LoginVM;
import me.elhakimi.hunters_league.web.vm.request.UserUpdateVm;
import me.elhakimi.hunters_league.web.vm.response.UserResponseVm;


@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toUser(CreatNewUserVm creatNewUserVm);
    AppUser toUserFromLoginVm(LoginVM loginVM);
    CreatNewUserVm toUserVm(AppUser appUser);
    UserResponseVm toUserResponseVm(AppUser appUser);
    AppUser toUserFromUpdateVm(UserUpdateVm userUpdateVm);

}
