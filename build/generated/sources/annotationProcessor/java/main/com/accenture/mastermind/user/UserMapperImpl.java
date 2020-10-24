package com.accenture.mastermind.user;

import com.accenture.mastermind.user.UserDTO.withPass;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-24T15:17:25+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 12.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDTO(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setId( dto.getId() );

        return user;
    }

    @Override
    public List<User> fromDTOs(List<UserDTO> users) {
        if ( users == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( users.size() );
        for ( UserDTO userDTO : users ) {
            list.add( fromDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );

        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public withPass toWithPassword(User user) {
        if ( user == null ) {
            return null;
        }

        withPass withPass = new withPass();

        withPass.setId( user.getId() );
        withPass.setUsername( user.getUsername() );
        withPass.setPassword( user.getPassword() );

        return withPass;
    }

    @Override
    public User fromWithPassword(withPass dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );
        user.setId( dto.getId() );

        return user;
    }

    @Override
    public List<withPass> toWithPasswords(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<withPass> list = new ArrayList<withPass>( user.size() );
        for ( User user1 : user ) {
            list.add( userTowithPass( user1 ) );
        }

        return list;
    }

    @Override
    public List<User> fromWithPasswords(List<withPass> usersDto) {
        if ( usersDto == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( usersDto.size() );
        for ( withPass withPass : usersDto ) {
            list.add( fromWithPassword( withPass ) );
        }

        return list;
    }

    protected withPass userTowithPass(User user) {
        if ( user == null ) {
            return null;
        }

        withPass withPass = new withPass();

        withPass.setId( user.getId() );
        withPass.setUsername( user.getUsername() );
        withPass.setPassword( user.getPassword() );

        return withPass;
    }
}
