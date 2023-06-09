package pl.kubab16.githubapirekchallenge.untils;

public class Branch {
    private final String name;
    private final String lastCommitSha;

    public Branch(String name, String lastCommitSha) {
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }

    public String getName() {
        return this.name;
    }

    public String getLastCommitSha() {
        return this.lastCommitSha;
    }
}
