package com.chronosx.bikeshop.service;

import java.text.ParseException;

import com.chronosx.bikeshop.dto.request.AuthenticationRequest;
import com.chronosx.bikeshop.dto.request.IntrospectRequest;
import com.chronosx.bikeshop.dto.request.LogoutRequest;
import com.chronosx.bikeshop.dto.request.RefreshTokenRequest;
import com.chronosx.bikeshop.dto.response.AuthenticationResponse;
import com.chronosx.bikeshop.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request) throws JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    IntrospectResponse introspect(IntrospectRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
}
