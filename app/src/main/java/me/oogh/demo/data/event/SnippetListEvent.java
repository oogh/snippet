package me.oogh.demo.data.event;

import java.util.List;

import me.oogh.demo.data.Snippet;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetListEvent {
    public enum Tag {
        SNIPPET_LIST
    }

    public Tag tag;
    public List<Snippet> snippets;

    public SnippetListEvent(Tag tag, List<Snippet> snippets) {
        this.tag = tag;
        this.snippets = snippets;
    }
}
