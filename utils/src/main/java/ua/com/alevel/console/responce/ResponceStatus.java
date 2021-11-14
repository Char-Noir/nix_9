package ua.com.alevel.console.responce;

public enum ResponceStatus {
    OK(200, "Ok", "Returns when everything is okay"),
    EROOR(500, "Errow", "Something went wrong"),
    OK_REDIRECT(308, "Ok and Redirect", "Redirect to somewhere when is OK");

    private final int statusId;
    private final String name;
    private final String description;

    ResponceStatus(int id, String name, String description) {
        this.statusId = id;
        this.name = name;
        this.description = description;
    }
}
