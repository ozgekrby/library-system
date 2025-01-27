package model;

public class Admin extends Person {
    private String secretKey;

    public Admin(String id, String name, String secretKey) {
        super(id, name);
        this.secretKey = secretKey;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public boolean validateSecretKey(String key) {
        return this.secretKey.equals(key);
    }
}