package pl.kubab16.githubapirekchallenge.untils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Response {
    private final HttpStatus status = HttpStatus.OK;
    private final List<Repository> repositories;

    public Response(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }


    public HttpStatus getStatus() {
        return status;
    }
}
