package learning.job01;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Job01App {

    public static void main(String[] args) throws Exception {
        Path dir = Path.of(System.getProperty("java.io.tmpdir"), "java-frontend-top-path-job01");
        Files.createDirectories(dir);
        Path file = dir.resolve("hello.txt");
        String line = "hello from job01 at " + System.currentTimeMillis() + "\n";
        Files.writeString(file, line, StandardCharsets.UTF_8);
        String readBack = Files.readString(file, StandardCharsets.UTF_8);
        System.out.print(readBack);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());
        System.out.println("http status: " + response.statusCode());
        System.out.println("json title: " + root.path("title").asText());
    }
}
