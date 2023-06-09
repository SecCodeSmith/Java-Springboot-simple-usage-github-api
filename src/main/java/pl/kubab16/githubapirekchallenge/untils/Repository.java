package pl.kubab16.githubapirekchallenge.untils;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private String ownerUsername;
    private String name;
    private List<Branch> branchList;

    public Repository(String ownerUsername, String name) {
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.branchList = new ArrayList<>();
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public void addBranchToList(Branch branch) {
        branchList.add(branch);
    }
}
