package models;

public class TokenResponse {

    private String token;
    private String expires;
    private String status;
    private String result;

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for expires
    public String getExpires() {
        return expires;
    }

    // Setter for expires
    public void setExpires(String expires) {
        this.expires = expires;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for result
    public String getResult() {
        return result;
    }

    // Setter for result
    public void setResult(String result) {
        this.result = result;
    }
}