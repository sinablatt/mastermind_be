package com.accenture.mastermind.user;


import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  User fromDTO(UserDTO dto);

  List<User> fromDTOs(List<UserDTO> users);

  @Named(value = "toUserDTO")
  UserDTO toDTO(User user);

  @IterableMapping(qualifiedByName = "toUserDTO")
  List<UserDTO> toDTOs(List<User> users);

  @Named("toWithPassword")
  UserDTO.withPass toWithPassword(User user);

  User fromWithPassword(UserDTO.withPass dto);

  @Named("toWithPasswords")
  List<UserDTO.withPass> toWithPasswords(List<User> user);

  @Named("fromWithPasswords")
  List<User> fromWithPasswords(List<UserDTO.withPass> usersDto);

  @Named("toUserDTO")
  List<UserDTO> toUserDTO(List<User> user);

  @Named("fromUserDTO")
  List<User> fromUserDTO(List<UserDTO> usersDto);
}
