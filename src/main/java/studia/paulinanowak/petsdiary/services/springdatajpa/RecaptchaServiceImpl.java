package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import studia.paulinanowak.petsdiary.controllers.response.RecaptchaResponse;
import studia.paulinanowak.petsdiary.services.RecaptchaService;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

    private final String RECAPTCHA_SECRET = "6Lc61gwpAAAAAPaAE7YMXKk53PKiFAVGA3L0vWal";

    @Override
    public boolean validateCaptcha(String captcha){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("secret", RECAPTCHA_SECRET);
        requestMap.add("response", captcha);

        RecaptchaResponse apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, RecaptchaResponse.class);
        if(apiResponse == null){
            return false;
        }

        return Boolean.TRUE.equals(apiResponse.getSuccess());
    }
}
