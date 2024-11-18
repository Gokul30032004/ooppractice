import java.io.FileInputStream;
import java.util.Properties;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class ApiJsonOutput {

    public static void main(String[] args) {
        String urlString = getApiUrlFromProperties();
        if (urlString == null) {
            System.out.println("API URL not found in properties file.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        try {
            // Create a request using OkHttp
            Request request = new Request.Builder()
                    .url(urlString)
                    .build();

            // Send the request and get the response
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    // Convert the response to JSON
                    JSONObject jsonOutput = new JSONObject(response.body().string());
                    System.out.println(jsonOutput.toString(4)); // Pretty print the JSON output
                } else {
                    System.out.println("GET request failed. Response Code: " + response.code());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getApiUrlFromProperties() {
        Properties properties = new Properties();
        String apiUrl = null;

        try (FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Documents\\input.properties")) {
            properties.load(fis);
            apiUrl = properties.getProperty("api.url");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiUrl;
    }
}
