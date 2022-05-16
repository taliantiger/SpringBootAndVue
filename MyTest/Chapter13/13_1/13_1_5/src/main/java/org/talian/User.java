package org.talian;

public class User {
    private String username;
    private String gender;

    @Override
    public String toString(){
        return "{" +
                "username=" + username + '\'' +
                ", gender=" + gender + '\'' +
                "}";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
