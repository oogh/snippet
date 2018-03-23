package me.oogh.demo.data.event;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description EventBus中的事件POJO
 */

public class SnippetEvent {
    public enum Tag {
        REMOVE_ITEM
    }

    public Tag tag;
    public String id;

    public SnippetEvent(Tag tag, String id) {
        this.tag = tag;
        this.id = id;
    }


}
