package com.macons.apparkme.mapper;

import com.macons.apparkme.dto.UserDTO;
import com.macons.apparkme.dto.UserRequest;
import com.macons.apparkme.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO personaToPersonaDto(User user);
    User userDTOToUser(UserDTO user);

    User userRequestToUser(UserRequest userRequest);

}
