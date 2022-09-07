import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class Consumer {

  public static void main(String[] args) {
   RestTemplate restTemplate = new RestTemplate();
   //get
   String url = "https://reqres.in/api/users/2";
   String response = restTemplate.getForObject(url, String.class);
    System.out.println(response);

    //post
    String url2 = "https://reqres.in/api/users/";
    Map<String, String> jsonToSend = new HashMap<>();
    jsonToSend.put("name", "Test name");
    jsonToSend.put("job", "Test job");
    HttpEntity<Map <String, String>> request = new HttpEntity<>(jsonToSend);
    String response2 = restTemplate.postForObject(url,request, String.class);
    System.out.println(response2);

  }

}
