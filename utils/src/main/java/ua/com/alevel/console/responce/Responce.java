package ua.com.alevel.console.responce;

public class Responce {

    ResponceStatus status;
    String redirectTo;
    String errorMessage;
    String message;

    public Responce(ResponceStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public static Responce getOk(String message) {
        return new Responce(ResponceStatus.OK, message);
    }

    public static Responce getError(String message, String eroorMessage) {
        Responce responce = new Responce(ResponceStatus.EROOR, message);
        responce.errorMessage = eroorMessage;
        return responce;
    }

    public static Responce getRedirect(String message, String redirectTo) {
        Responce responce = new Responce(ResponceStatus.OK_REDIRECT, message);
        responce.redirectTo = redirectTo;
        return responce;
    }

    public ResponceStatus getStatus() {
        return status;
    }

    public String getRedirectTo() {
        return redirectTo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getMessage() {
        return message;
    }
}
