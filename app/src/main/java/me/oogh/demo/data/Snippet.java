package me.oogh.demo.data;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class Snippet {
    private String id;
    private String Content;

    @Override
    public String toString() {
        return "SnippetEntry{" +
                "id='" + id + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Snippet() {

    }

    public Snippet(String id, String content) {
        this.id = id;
        Content = content;
    }
}
