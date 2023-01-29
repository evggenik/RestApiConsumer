import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

public class Consumer {
    public static void main(String[] args) {

        String url = "http://94.198.50.185:7081/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 42);
        // GET
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response1 = restTemplate.getForEntity(url, String.class);
        // POST
        String cookie = response1.getHeaders().getFirst("set-cookie");
        headers.add("Cookie", cookie); // Почему куки нужно назвать Cookie?
        HttpEntity<User> request1 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response2 = restTemplate.postForEntity(url, request1, String.class);
        System.out.println(response2.getBody());
        // PUT
        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> request2 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response3 = restTemplate.exchange(url, HttpMethod.PUT, request2, String.class);
        System.out.println(response3.getBody());
        // DELETE
        HttpEntity<User> request3 = new HttpEntity<>(headers);
        ResponseEntity<String> response4 = restTemplate.exchange(url+"/3", HttpMethod.DELETE, request3, String.class);
        System.out.println(response4.getBody());
    }
}
