package ua.com.alevel.web.dto.response;

public abstract class ResponseDto {

    protected Long id;
    private Boolean visible = true;

    public ResponseDto() {
    }

    public ResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
