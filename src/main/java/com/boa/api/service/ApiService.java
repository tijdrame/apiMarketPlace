package com.boa.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.domain.ParamEndPoint;
import com.boa.api.domain.Tracking;
import com.boa.api.request.OAuthRequest;
import com.boa.api.response.GenericResponse;
import com.boa.api.service.util.ICodeDescResponse;
import com.boa.api.service.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiService {
    
    private final Logger log = LoggerFactory.getLogger(ApiService.class);
    private final TrackingService trackingService;
    private final UserService userService;
    private final Utils utils;
    private final ParamEndPointService endPointService;

    public ApiService(TrackingService trackingService, UserService userService, Utils utils,
            ParamEndPointService endPointService) {
        this.trackingService = trackingService;
        this.userService = userService;
        this.utils = utils;
        this.endPointService = endPointService;
    }

	public GenericResponse oAuth(OAuthRequest authRequest, HttpServletRequest request) {
        log.info("Enter in assetFin=== [{}]", authRequest);
        GenericResponse genericResp = new GenericResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        Optional<ParamEndPoint> endPoint = endPointService.findByCodeParam("oAuth");
        if (!endPoint.isPresent()) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "authorisation", genericResp.toString(),
            authRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            String jsonStr = new JSONObject().put("login", authRequest.getLogin())
            .put("pass", authRequest.getPassword())
            .put("country", authRequest.getCountry())
            .toString();
            HttpURLConnection conn = utils.doConnexion(endPoint.get().getEndPoints(), jsonStr, "application/json", null);
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                // result = IOUtils.toString(conn.getInputStream(), "UTF-8");
                log.info("oAuth result ===== [{}]", result);
                obj = new JSONObject(result);
                obj = obj.getJSONObject("data");
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(result, Map.class);
                //obj = new JSONObject(result);
                //obj = obj.getJSONObject("InfoTransaction");
                genericResp.setData(map);
                /*genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), assetRequest.toString());*/
                //fin todo

                if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0100")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), authRequest.toString(), genericResp.getResponseReference());
                } else {
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                            genericResp.toString(), authRequest.toString(), genericResp.getResponseReference());
                }
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi error ===== [{}]", result);
                obj = new JSONObject(result);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(result, Map.class);
                obj = new JSONObject(result);
                genericResp.setData(map);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), authRequest.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in oAuth [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION + " " + e.getMessage());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                    authRequest.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
		return genericResp;
    }
    
    public Tracking createTracking(Tracking tracking, String code, String endPoint, String result, String req
    , String reqId) {
        // Tracking tracking = new Tracking();
        tracking.setRequestId(reqId);
        tracking.setCodeResponse(code);
        tracking.setDateResponse(Instant.now());
        tracking.setEndPoint(endPoint);
        tracking.setLoginActeur(userService.getUserWithAuthorities().get().getLogin());
        tracking.setResponseTr(result);
        tracking.setRequestTr(req);
        return tracking;
    }
}
