package model;




public class Article {

    private Long idArticle;
    private Long articleBarcode;
    private String articleName;
    private Double articlePrice;
    private Integer articleQuantity;
    private Boolean isArticleAvailable;

    public Article() {
    }

    public Article(Long idArticle, Long articleBarcode, String articleName, Double articlePrice, Integer articleQuantity, Boolean isArticleAvailable) {
        this.idArticle = idArticle;
        this.articleBarcode = articleBarcode;
        this.articleName = articleName;
        this.articlePrice = articlePrice;
        this.articleQuantity = articleQuantity;
        this.isArticleAvailable = isArticleAvailable;
    }

    public Long getIdArticle() {
        return this.idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public Long getArticleBarcode() {
        return this.articleBarcode;
    }

    public void setArticleBarcode(Long articleBarcode) {
        this.articleBarcode = articleBarcode;
    }

    public String getArticleName() {
        return this.articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Double getArticlePrice() {
        return this.articlePrice;
    }

    public void setArticlePrice(Double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public Integer getArticleQuantity() {
        return this.articleQuantity;
    }

    public void setArticleQuantity(Integer articleQuantity) {
        this.articleQuantity = articleQuantity;
    }

    public Boolean isArticleAvailable() {
        return this.isArticleAvailable;
    }

    public Boolean getIsArticleAvailable() {
        return this.isArticleAvailable;
    }

    public void setIsArticleAvailable(Boolean isArticleAvailable) {
        this.isArticleAvailable = isArticleAvailable;
    }

}
