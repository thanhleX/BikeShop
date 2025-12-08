package com.chronosx.bikeshop.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronosx.bikeshop.dto.request.AuthenticationRequest;
import com.chronosx.bikeshop.dto.request.IntrospectRequest;
import com.chronosx.bikeshop.dto.request.LogoutRequest;
import com.chronosx.bikeshop.dto.request.RefreshTokenRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.AuthenticationResponse;
import com.chronosx.bikeshop.dto.response.IntrospectResponse;
import com.chronosx.bikeshop.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }

    @PostMapping("logout")
    public ApiResponse<?> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.builder().build();
    }

    @PostMapping("refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }
}
