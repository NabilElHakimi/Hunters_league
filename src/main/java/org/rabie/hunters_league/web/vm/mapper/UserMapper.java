package org.rabie.hunters_league.web.vm.mapper;

import org.mapstruct.Mapper;
import org.rabie.hunters_league.domain.AppUser;

import org.rabie.hunters_league.web.vm.request.CreatNewUserVm;
import org.rabie.hunters_league.web.vm.request.LoginVM;
import org.rabie.hunters_league.web.vm.request.UserUpdateVm;
import org.rabie.hunters_league.web.vm.response.UserResponseVm;


@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toUser(CreatNewUserVm creatNewUserVm);
    AppUser toUserFromLoginVm(LoginVM loginVM);
    CreatNewUserVm toUserVm(AppUser appUser);
    UserResponseVm toUserResponseVm(AppUser appUser);
    AppUser toUserFromUpdateVm(UserUpdateVm userUpdateVm);

}
