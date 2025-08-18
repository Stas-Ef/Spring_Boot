package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

final public class Article implements Searchable {
    private final String name;
    private final String text;
    private final UUID id;


    public Article(UUID id, String name, String text) {
        this.name = name;
        this.text = text;
        this.id = id;
    }

    public String getproductName() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " " + '\'' +
                text + '\'';
    }

    @JsonIgnore

    public String searchTerm() {
        return toString();
    }

    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Article article)) return false;
        return Objects.equals(name, article.name) && Objects.equals(text, article.text) && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text);
    }
}
