package functions;

import exception.HttpClientException;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ClientHttp {

    private static Properties getProp() {
        Properties prop = new Properties();
        try (FileInputStream file = new FileInputStream("src/Resourses/url.properties")) {
            prop.load(file);
            return prop;
        } catch (IOException ex) {
            throw new HttpClientException("Erro ao consultar a url");
        }
    }
    public String SearchData(String server){
        try {
            String url;
            Properties prop = getProp();
            url = prop.getProperty(server);
            URI address = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return body;
        }catch (IOException | InterruptedException ex){
            throw new HttpClientException("Erro ao conectar com api:");
        }
    }
}

