package com.boa.api.web.rest;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.request.OAuthRequest;
import com.boa.api.request.SearchClientRequest;
import com.boa.api.response.GenericResponse;
import com.boa.api.response.OAuthResponse;
import com.boa.api.response.SearchClientResponse;
import com.boa.api.service.ApiService;
import com.boa.api.service.util.ICodeDescResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiResource {
    
    private final Logger log = LoggerFactory.getLogger(ApiResource.class);

    private final ApiService apiService; 

    public ApiResource(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/oAuth")
    public ResponseEntity<OAuthResponse> oAuth(@RequestBody OAuthRequest authRequest, HttpServletRequest request) {
        log.debug("REST request to assetFin : [{}]", authRequest);
        OAuthResponse response = new OAuthResponse();
        if (controleParam(authRequest.getCountry()) || controleParam(authRequest.getLogin())||
         controleParam(authRequest.getPassword()) || controleParam(authRequest.getLangue())) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.badRequest().header("Authorization", request.getHeader("Authorization")).body(response);
        }
        response = apiService.oAuth(authRequest, request);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/getClients")
    public ResponseEntity<SearchClientResponse> getClients(@RequestBody SearchClientRequest clientRequest, HttpServletRequest request) {
        log.debug("REST request to getClients : [{}]", clientRequest);
        SearchClientResponse response = new SearchClientResponse();
        if (controleParam(clientRequest.getClient()) || controleParam(clientRequest.getUserCode()) ||
         controleParam(clientRequest.getCountry()) || controleParam(clientRequest.getLangue())) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.badRequest().header("Authorization", request.getHeader("Authorization")).body(response);
        }
        response = apiService.getClients(clientRequest, request);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    private Boolean controleParam(String param) {
        Boolean flag = false;
        if (StringUtils.isEmpty(param))
            flag = true;
        return flag;
    }
}
