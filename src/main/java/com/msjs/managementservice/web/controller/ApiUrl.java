package com.msjs.managementservice.web.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by jakub on 29.03.2017.
 */
public final class ApiUrl {
    public static final String AUTH_ENDPOINT = "/api/auth";
    public static final String TOKEN_REFRESH_ENDPOINT = "/api/auth/refresh";
    public static final String TOKEN_HEADER = "Authorization";

    static final String ID_PATH_PARAM = "/{id}";
    static final String API = "/api";

    static final String USERS = API + "/users";
    static final String ROLE = "/role";
    static final String USER_TASKS = ID_PATH_PARAM + "/tasks";
    static final String UNIQUE_FIELDS = "/unique-fields";
    static final String SHORT = "/short";

    static final String TASKS = API + "/tasks";

    static final String AUTHENTICATION = API + "/auth";
    static final String REFRESH = "/refresh";
    static final String LOCATION_HEADER = "Location";

    public static URI getLocationURI(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID_PATH_PARAM)
                .buildAndExpand(id).toUri();
    }
}
