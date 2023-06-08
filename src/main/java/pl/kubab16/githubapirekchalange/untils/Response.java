package pl.kubab16.githubapirekchalange.untils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Response {
    private List<Repository> repositories;
    private final HttpStatus status = HttpStatus.OK;

    public Response(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
