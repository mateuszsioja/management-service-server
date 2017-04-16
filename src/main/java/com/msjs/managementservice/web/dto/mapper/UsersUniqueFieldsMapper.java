package com.msjs.managementservice.web.dto.mapper;

import com.msjs.managementservice.core.model.UsersUniqueFieldsView;
import com.msjs.managementservice.web.dto.UsersUniqueFieldsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersUniqueFieldsMapper {

    public UsersUniqueFieldsDto toDto(List<UsersUniqueFieldsView> usersUniqueFieldsViews) {
        UsersUniqueFieldsDto uniqueFieldsDto = new UsersUniqueFieldsDto();
        uniqueFieldsDto.setUsernames(usersUniqueFieldsViews.stream()
                .map(UsersUniqueFieldsView::getUsername).collect(Collectors.toList()));
        uniqueFieldsDto.setEmails(usersUniqueFieldsViews.stream()
                .map(UsersUniqueFieldsView::getEmail).collect(Collectors.toList()));
        return uniqueFieldsDto;
    }
}
