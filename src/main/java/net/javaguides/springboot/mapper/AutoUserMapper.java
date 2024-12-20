package net.javaguides.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    // @Mapping(source = "email", target = "emailAddress")
    // Mapping can help to convert the field name if it's different between source and target
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
