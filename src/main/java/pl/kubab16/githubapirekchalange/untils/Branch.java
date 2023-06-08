package pl.kubab16.githubapirekchalange.untils;

public class Branch {
    private String name;
    private String lastCommitSha;

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
