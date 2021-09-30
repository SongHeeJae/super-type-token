package kuke.supertypetoken;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static kuke.supertypetoken.SupertypetokenApplication.*;

public class SpringTypeReference {
    public static void main(String[] args) {

//        ParameterizedTypeReference typeRef = new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>() {};
//        System.out.println("typeRef.getType() = " + typeRef.getType());
        RestTemplate rt = new RestTemplate();

//        List<User> users = rt.getForObject("http://localhost:8080", List.class);
        List<User> users = rt.exchange("http://localhost:8080", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
        users.forEach(System.out::println);
    }
}
