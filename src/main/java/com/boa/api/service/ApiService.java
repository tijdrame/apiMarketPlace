package com.boa.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.domain.ParamEndPoint;
import com.boa.api.domain.Tracking;
import com.boa.api.request.CreateLoanRequest;
import com.boa.api.request.OAuthRequest;
import com.boa.api.request.SearchClientRequest;
import com.boa.api.response.CreateLoanResponse;
import com.boa.api.response.GenericResponse;
import com.boa.api.response.OAuthResponse;
import com.boa.api.response.SearchClientResponse;
import com.boa.api.response.model.Account;
import com.boa.api.response.model.Client;
import com.boa.api.service.util.ICodeDescResponse;
import com.boa.api.service.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.MessageSource;
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
    private final MessageSource messageSource;

    public ApiService(TrackingService trackingService, UserService userService, Utils utils,
            ParamEndPointService endPointService, MessageSource messageSource) {
        this.trackingService = trackingService;
        this.userService = userService;
        this.utils = utils;
        this.endPointService = endPointService;
        this.messageSource = messageSource;
    }

    public OAuthResponse oAuth(OAuthRequest authRequest, HttpServletRequest request) {
        log.info("Enter in oAuth=== [{}]", authRequest);
        Locale locale = defineLocale(authRequest.getLangue());

        OAuthResponse genericResp = new OAuthResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        Optional<ParamEndPoint> endPoint = endPointService.findByCodeParam("oAuth");
        if (!endPoint.isPresent()) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(messageSource.getMessage("service.absent", null, locale));
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "authorisation", genericResp.toString(),
                    authRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            String jsonStr = new JSONObject().put("login", authRequest.getLogin())
                    .put("pass", authRequest.getPassword()).put("country", authRequest.getCountry()).toString();
            HttpURLConnection conn = utils.doConnexion(endPoint.get().getEndPoints(), jsonStr, "application/json",
                    null);
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

                if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0100")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(messageSource.getMessage("auth.success", null, locale));
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setUserCode(obj.getString("rucode"));
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), authRequest.toString(), genericResp.getResponseReference());
                } else {
                    String ret = getMsgEchecAuth(obj, locale);
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ret);
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
                /*ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(result, Map.class);*/
                obj = new JSONObject(result);
                // genericResp.setData(map);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(messageSource.getMessage("auth.error.exep", null, locale));
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), authRequest.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in oAuth [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            // genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION + " " +
            // e.getMessage());
            genericResp.setDescription(messageSource.getMessage("auth.error.exep", null, locale) + e.getMessage());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                    authRequest.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public SearchClientResponse getClients(SearchClientRequest clientRequest, HttpServletRequest request) {
        log.info("Enter in getClients=== [{}]", clientRequest);
        Locale locale = defineLocale(clientRequest.getLangue());

        SearchClientResponse genericResp = new SearchClientResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        Optional<ParamEndPoint> endPoint = endPointService.findByCodeParam("getClients");
        if (!endPoint.isPresent()) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(messageSource.getMessage("service.absent", null, locale));
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "authorisation", genericResp.toString(),
            clientRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            String jsonStr = new JSONObject().put("customerId", clientRequest.getClient())
                    .put("country", clientRequest.getCountry()).put("userCode", clientRequest.getUserCode()).toString();
            HttpURLConnection conn = utils.doConnexion(endPoint.get().getEndPoints(), jsonStr, "application/json",
                    null);
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
                log.info("getClients result ===== [{}]", result);
                obj = new JSONObject(result);
                obj = obj.getJSONObject("data");

                if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0100")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(messageSource.getMessage("client.success", null, locale));
                    genericResp.setDateResponse(Instant.now());

                    Client client = new Client();
                    client
                    .civilite(obj.getString("civility"))
                    .firstName(obj.getString("fname"))
                    .lastName(obj.getString("name"));

                    Account account = new Account();
                    //TODO obj.getJSONObject("rdata") doit être une liste
                    account
                    .accountNum(obj.getJSONObject("rdata").getString("accountnumber"))
                    .branchName(obj.getJSONObject("rdata").getString("branchname"))
                    .accountname(obj.getJSONObject("rdata").getString("accountname"));
                    client.getAccounts().add(account);
                    
                    genericResp.client(client);
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), clientRequest.toString(), genericResp.getResponseReference());
                } else {
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(messageSource.getMessage("client.error", null, locale));
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                            genericResp.toString(), clientRequest.toString(), genericResp.getResponseReference());
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
                
                obj = new JSONObject(result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(messageSource.getMessage("auth.error.exep", null, locale));
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), clientRequest.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in oAuth [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            // genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION + " " +
            // e.getMessage());
            genericResp.setDescription(messageSource.getMessage("auth.error.exep", null, locale) + e.getMessage());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
            clientRequest.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public CreateLoanResponse createLoan(CreateLoanRequest loanRequest, HttpServletRequest request) {
        log.info("Enter in createLoan=== [{}]", loanRequest);
        Locale locale = defineLocale(loanRequest.getLangue());

        CreateLoanResponse genericResp = new CreateLoanResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        Optional<ParamEndPoint> endPoint = endPointService.findByCodeParam("createLoan");
        if (!endPoint.isPresent()) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(messageSource.getMessage("service.absent", null, locale));
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "authorisation", genericResp.toString(),
            loanRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            String jsonStr = new JSONObject()
                    .put("userCode", loanRequest.getUserCode())
                    .put("lduration", loanRequest.getDuration())
                    .put("lclient", loanRequest.getClient())
                    .put("caccountnum", loanRequest.getAccountNum())
                    .put("lamount", loanRequest.getAmount())
                    .put("ldocref", loanRequest.getDocRef())
                    .put("cemployer", loanRequest.getCliEmployer())
                    .put("supcode", loanRequest.getSupplierCode())
                    .put("supname", loanRequest.getSupplierName())
                    .put("lfees", loanRequest.getFees())
                    .put("country", loanRequest.getCountry())
                    .put("lassureur", loanRequest.getAssureur())
                    .put("lassuramount", loanRequest.getAssurAmount())
                    .put("creditCode", loanRequest.getCreditCode())
                    .toString();
            HttpURLConnection conn = utils.doConnexion(endPoint.get().getEndPoints(), jsonStr, "application/json",
                    null);
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
                log.info("createLoan result ===== [{}]", result);
                obj = new JSONObject(result);
                obj = obj.getJSONObject("data");

                if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0100")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(messageSource.getMessage("loan.success", null, locale));
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setRefIngec(obj.getJSONObject("rdata").getString("refIngec"));
                    
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), loanRequest.toString(), genericResp.getResponseReference());
                }
                 else {
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(getMsgErrorLoan(obj.getString("rcode"), locale));
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                            genericResp.toString(), loanRequest.toString(), genericResp.getResponseReference());
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
                
                obj = new JSONObject(result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(messageSource.getMessage("auth.error.exep", null, locale));
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), loanRequest.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in create Loan [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(messageSource.getMessage("auth.error.exep", null, locale) + e.getMessage());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
            loanRequest.toString(), genericResp.getResponseReference());
        }
		trackingService.save(tracking);
        return genericResp;
	}
    
    private Locale defineLocale(String lang) {
        Locale locale = null;
        if (lang.equalsIgnoreCase("en"))
            locale = Locale.ENGLISH;
        else
            locale = Locale.FRANCE;
            return locale;
    }

    private String getMsgErrorLoan(String retour, Locale locale){
        if(retour.equals("0200")){
            return messageSource.getMessage("loan.code.error", null, locale);
        } else if(retour.equals("0201")){
            return messageSource.getMessage("loan.credit.error", null, locale);
        }else if(retour.equals("0202")){
            return messageSource.getMessage("loan.compte.error", null, locale);
        }else if(retour.equals("0203")){
            return messageSource.getMessage("loan.tit.error", null, locale);
        }else if(retour.equals("0204")){
            return messageSource.getMessage("loan.emp.error", null, locale);
        }else {
            return messageSource.getMessage("auth.error.exep", null, locale);
        }
    }

    private String getMsgEchecAuth(JSONObject obj, Locale locale) {
        log.info("in getMsgEchecAuth [{}]", obj.toString());
        try {
            if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0202")) {
                return messageSource.getMessage("auth.error.0202", null, locale);
            } else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0203")) {
                return messageSource.getMessage("auth.error.0203", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0204")) {
                String msg = obj.getString("rmessage");
            final String[] params = new String[]{msg};
                return messageSource.getMessage("auth.error.0204", params, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0205")) {
                return messageSource.getMessage("auth.error.0205", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0206")) {
                return messageSource.getMessage("auth.error.0206", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0207")) {
                return messageSource.getMessage("auth.error.0207", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0208")) {
                return messageSource.getMessage("auth.error.0208", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0209")) {
                return messageSource.getMessage("auth.error.0209", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0210")) {
                return messageSource.getMessage("auth.error.0210", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0211")) {
                return messageSource.getMessage("auth.error.0211", null, locale);
            }else if (obj.toString() != null && !obj.isNull("rcode") && obj.get("rcode").equals("0101")) {
                return messageSource.getMessage("auth.error.0101", null, locale);
            }
        } catch (Exception e) {
            return messageSource.getMessage("auth.error.exep", null, locale) + e.getMessage();
        }
        return messageSource.getMessage("auth.error.exep", null, locale);
    }

    public Tracking createTracking(Tracking tracking, String code, String endPoint, String result,
            String req
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
