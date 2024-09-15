package api;

public class SignUpRequest {
    private String name;
    private String email;
    private String password;

    public SignUpRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
}
