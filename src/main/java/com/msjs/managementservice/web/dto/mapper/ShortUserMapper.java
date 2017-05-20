package com.msjs.managementservice.web.dto.mapper;

import com.msjs.managementservice.core.model.ShortUser;
import com.msjs.managementservice.web.dto.ShortUserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by jakub on 20.05.2017.
 */
@Component
public class ShortUserMapper {

    public List<ShortUserDto> mapToDto(List<ShortUser> shortUsers) {
        return shortUsers.stream().map(mapToShortUserDto()).collect(Collectors.toList());
    }

    private Function<ShortUser, ShortUserDto> mapToShortUserDto() {
        return s -> new ShortUserDto(s.getId(), convertToFullName(s.getFirstName(), s.getLastName()));
    }

    private String convertToFullName(String firstName, String lastName) {
        StringBuilder builder = new StringBuilder(firstName);
        builder.append(" ");
        builder.append(lastName);
        return builder.toString();
    }
}
