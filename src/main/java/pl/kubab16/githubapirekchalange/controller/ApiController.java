package pl.kubab16.githubapirekchalange.controller;

import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.kubab16.githubapirekchalange.untils.Branch;
import pl.kubab16.githubapirekchalange.untils.ErrorResponse;
import pl.kubab16.githubapirekchalange.untils.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private static final String ACCEPT_HEADER_JSON = "application/json";

    private List<Repository> getUserRepositories(String username, String acceptHeader, String token)
            throws IOException, InterruptedException {
        String url = GITHUB_API_URL + "/users/" + username + "/repos";

        if (!acceptHeader.equals(ACCEPT_HEADER_JSON)) {
            throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE, "Accept only " + ACCEPT_HEADER_JSON + ".");
        }


        HttpResponse<String> response = sendRequest(acceptHeader, token, url);

        List<Repository> repositories = new ArrayList<>();

        if (response.statusCode() == 404) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User don't found.");
        }
        JSONArray json = new JSONArray(response.body());

        for (Object object: json) {
            JSONObject obj = (JSONObject) object;
            if (obj.getBoolean("fork")) {
                continue;
            }

            Repository repository = new Repository(obj.getJSONObject("owner").getString("login"),
                    obj.getString("name"));

            String branch_url = GITHUB_API_URL + "/repos/" + username + "/" + repository.getName() + "/branches";


            HttpResponse<String> response_branch = sendRequest(acceptHeader, token, branch_url);
            JSONArray json_branch = new JSONArray(response_branch.body());

            for (Object object_b: json_branch) {
                JSONObject obj_b = (JSONObject) object_b;
                Branch branch = new Branch(obj_b.getString("name"),
                        obj_b.getJSONObject("commit").getString("sha"));
                repository.addBranchToList(branch);
            }

            repositories.add(repository);
        }


        if (repositories == null || repositories.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User don't found.");
        }


        return repositories;
    }

    private HttpResponse<String> sendRequest(String acceptHeader, String token, String url)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", acceptHeader)
                .GET();

        if (token != null) {
            String authorizationHeader = "Bearer " + token;
            requestBuilder.header("Authorization", authorizationHeader);
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?>  getRepositoryList(
            @PathVariable String username,
            @RequestHeader("Accept") String acceptHeader,
            @RequestHeader("Token") @Nullable String token
    ) {
        try {
            List<Repository> repositories = getUserRepositories(username, acceptHeader, token);
            return ResponseEntity.ok(repositories);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getStatusCode().value(),
                    e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        }catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
