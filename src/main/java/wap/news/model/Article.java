package wap.news.model;

public class Article {
    private int id;
    private String title;
    private String body;
    private int categoryId;
    private String mainImage;
    private Boolean isNaveBar;
    private Boolean isRotating;
    private Boolean isActive;


    public Article(String title, String body, int categoryId, String mainImage, Boolean isNaveBar, Boolean isRotating, Boolean isActive) {
        this.title = title;
        this.body = body;
        this.categoryId = categoryId;
        this.mainImage = mainImage;
        this.isNaveBar = isNaveBar;
        this.isRotating = isRotating;
        this.isActive = isActive;
    }
    public Article(int id,String title, String body, int categoryId, String mainImage, Boolean isNaveBar, Boolean isRotating, Boolean isActive) {
        this.id=id;
        this.title = title;
        this.body = body;
        this.categoryId = categoryId;
        this.mainImage = mainImage;
        this.isNaveBar = isNaveBar;
        this.isRotating = isRotating;
        this.isActive = isActive;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Boolean getIsNaveBar() {
        return isNaveBar;
    }

    public void setIsNaveBar(Boolean naveBar) {
        isNaveBar = naveBar;
    }

    public Boolean getIsRotating() {
        return isRotating;
    }

    public void setIsRotating(Boolean rotating) {
        isRotating = rotating;
    }

}
