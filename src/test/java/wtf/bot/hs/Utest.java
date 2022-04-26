package wtf.bot.hs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class Utest {

    @Transactional
    @Test
    public void uTestRequest() {
        Map <String, String> propertiesMap = uTestReading();
        RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json;charset=cp1251");
//        headers.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36");
        headers.set("id", propertiesMap.get("pbId"));
        headers.set("token", propertiesMap.get("pbToken"));

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

//        ResponseEntity<String> response = restTemplate.execute(AppConstants.PB_CURRENCY_FP,)
        ResponseEntity<Map> responseMap = restTemplate.exchange(AppConstants.PB_CURRENCY_FP, HttpMethod.GET, entity, Map.class);

        System.out.println(responseMap.getBody());
//        log.info(String.valueOf(response.getBody()));
        log.info(responseMap.getBody().keySet().toString());
        log.info(responseMap.getBody().get("USD").getClass().getSimpleName());

        Map<String, Map<String, Map<String, Map <String, String>>>> testMap = (Map<String, Map<String, Map<String, Map <String, String>>>>) responseMap.getBody();
        log.info(testMap.get("USD").get("B").keySet().toString());

        log.info(String.valueOf(testMap.get("USD").get("B").get("date")));
        log.info(String.valueOf(testMap.get("USD").get("B").get("rate")));

        String dateTime = String.valueOf(testMap.get("USD").get("B").get("date"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

        Date date = null;
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        log.info(date!=null?date.toString():"null");

//        URI uri = null;
//        try {
//            uri = new URI(AppConstants.PB_CURRENCY_FP);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//        log.info(String.valueOf(result.getStatusCodeValue()));
//        log.info(result.getBody());

    }

    @Transactional
    @Test
    public Map<String, String> uTestReading() {

        String stringCanonicalPath = null;
        try {
            stringCanonicalPath = String.valueOf(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = stringCanonicalPath.substring(0, stringCanonicalPath.lastIndexOf("/")) + "/pb.properties";
        log.info(filePath);

        Map<String, String> propertiesMap = new HashMap();
        try (InputStream fileInputStream = new FileInputStream(filePath);){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            propertiesMap.put("pbId", properties.getProperty("pb.id"));
            propertiesMap.put("pbToken", properties.getProperty("pb.token"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return propertiesMap;
    }

}
