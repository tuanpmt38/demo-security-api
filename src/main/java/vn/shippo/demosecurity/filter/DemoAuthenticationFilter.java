package vn.shippo.demosecurity.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.shippo.demosecurity.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class DemoAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(DemoAuthenticationFilter.class);

    private static final String HEADER_TOKEN = "Authorization";

    private static final String OAUTH_SERVICE_URL = "http://192.168.2.253/oauthservice/me";

    private static final String REQUEST_ATTR_DO_NOT_CONTINUE = "MyAuthenticationFilter-doNotContinue";

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /** check the request true or false*/
        if (request instanceof HttpServletRequest == false) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = request.getHeader(HEADER_TOKEN);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HEADER_TOKEN, accessToken);

       //  HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Gửi yêu cầu với phương thức GET, và các thông tin Headers.
        ResponseEntity<String> respon = restTemplate.exchange(OAUTH_SERVICE_URL, HttpMethod.GET, entity, String.class);
        HttpStatus statusCode = respon.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);

        // Status Code: 200
        if (statusCode == HttpStatus.OK) {
            // Response Body Data
            String userInfo = respon.getBody();

            if (userInfo != null) {
                System.out.println("User info: "+userInfo);
            }
        }

        //get user's role by userid from

        filterChain.doFilter(request, response);

    }

}
