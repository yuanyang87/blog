package blog.geek.entity;

/**
 * 随笔
 * @author yuanyang
 * @version 1.0
 */
public class Article {

    private String articleId;   //编号
    private String articleTitle;    //随笔题目
    private String articleTime; //随笔发表时间
    private String articleContent;  //随笔内容(含代码一起存放)
    private String articleLink; //视频链接

    //start getter and setter
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(String articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    //end getter and setter
}
