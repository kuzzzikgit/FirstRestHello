package hello.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    private long id;
    private String name;

    @JsonCreator
    public Item(@JsonProperty("id") long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
