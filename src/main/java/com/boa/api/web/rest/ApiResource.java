package com.boa.api.web.rest;

import java.time.Instant;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.request.CreateLoanRequest;
import com.boa.api.request.NotifyPickupRequest;
import com.boa.api.request.OAuthRequest;
import com.boa.api.request.SearchClientRequest;
import com.boa.api.response.CreateLoanResponse;
import com.boa.api.response.GenericResponse;
import com.boa.api.response.NotifyPickupResponse;
import com.boa.api.response.OAuthResponse;
import com.boa.api.response.SearchClientResponse;
import com.boa.api.service.ApiService;
import com.boa.api.service.util.ICodeDescResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
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
    private final MessageSource messageSource; 

    public ApiResource(ApiService apiService, MessageSource messageSource) {
        this.apiService = apiService;
        this.messageSource = messageSource;
    }

    @PostMapping("/oAuth")
    public ResponseEntity<OAuthResponse> oAuth(@RequestBody OAuthRequest authRequest, HttpServletRequest request) {
        log.debug("REST request to assetFin : [{}]", authRequest);
        OAuthResponse response = new OAuthResponse();
        if (controleParam(authRequest.getCountry()) || controleParam(authRequest.getLogin())||
         controleParam(authRequest.getPassword()) || controleParam(authRequest.getLangue())) {
            Locale locale = defineLocale(authRequest.getLangue());
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(messageSource.getMessage("param.oblig", null, locale));
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
            Locale locale = defineLocale(clientRequest.getLangue());
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(messageSource.getMessage("param.oblig", null, locale));
            return ResponseEntity.badRequest().header("Authorization", request.getHeader("Authorization")).body(response);
        }
        response = apiService.getClients(clientRequest, request);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/createLoan")
    public ResponseEntity<CreateLoanResponse> createLoan(@RequestBody CreateLoanRequest loanRequest, HttpServletRequest request) {
        log.debug("REST request to createLoan : [{}]", loanRequest);
        CreateLoanResponse response = new CreateLoanResponse();
        if (controleParam(loanRequest.getUserCode()) || controleParam(loanRequest.getClient()) ||
         controleParam(loanRequest.getAccountNum()) || controleParam(loanRequest.getDocRef())||
         controleParam(loanRequest.getSupplierCode()) || controleParam(loanRequest.getSupplierName())||
         controleParam(loanRequest.getCountry()) || controleParam(loanRequest.getDuration()) || 
         controleParam(loanRequest.getAmount()) || controleParam(loanRequest.getFees()) || controleParam(loanRequest.getSalaireNet()) ||
         controleParam(loanRequest.getAssurAmount()) || controleParam(loanRequest.getAssureur())
         ) {
            Locale locale = defineLocale(loanRequest.getLangue());
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(messageSource.getMessage("param.oblig", null, locale));
            return ResponseEntity.badRequest().header("Authorization", request.getHeader("Authorization")).body(response);
        }
        response = apiService.createLoan(loanRequest, request);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/notifyPickup")
    public ResponseEntity<NotifyPickupResponse> notifyPickup(@RequestBody NotifyPickupRequest notifyRequest, HttpServletRequest request) {
        log.debug("REST request to notifyPickup : [{}]", notifyRequest);
        NotifyPickupResponse response = new NotifyPickupResponse();
        /*if (controleParam(loanRequest.getUserCode()) || controleParam(loanRequest.getClient()) ||
         controleParam(loanRequest.getAccountNum()) || controleParam(loanRequest.getDocRef())||
         controleParam(loanRequest.getSupplierCode()) || controleParam(loanRequest.getSupplierName())||
         controleParam(loanRequest.getCountry()) || controleParam(loanRequest.getDuration()) || 
         controleParam(loanRequest.getAmount()) || controleParam(loanRequest.getFees()) || controleParam(loanRequest.getSalaireNet()) ||
         controleParam(loanRequest.getAssurAmount()) || controleParam(loanRequest.getAssureur())
         ) {
            Locale locale = defineLocale(loanRequest.getLangue());
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(messageSource.getMessage("param.oblig", null, locale));
            return ResponseEntity.badRequest().header("Authorization", request.getHeader("Authorization")).body(response);
        }*/
        response = apiService.notifyPickup(notifyRequest, request);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    private Boolean controleParam(Object param) {
        Boolean flag = false;
        if (StringUtils.isEmpty(param))
            flag = true;
        return flag;
    }

    private Locale defineLocale(String lang) {
        Locale locale = null;
        if(StringUtils.isEmpty(lang)) lang = "en";
        if (lang.equalsIgnoreCase("en"))
            locale = Locale.ENGLISH;
        else
            locale = Locale.FRANCE;
            return locale;
    }
}
