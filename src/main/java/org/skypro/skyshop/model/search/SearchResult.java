package org.skypro.skyshop.model.search;

import org.skypro.skyshop.model.product.Product;

import java.util.Objects;

public final class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        String idStr = null;
        String name = null;
        String contentType = null;

        if (searchable != null) {
            idStr = searchable.getId().toString();
            name = searchable.getproductName();
            contentType = searchable.getContentType();
        } else {
            idStr = "Unknown";
            name = "Unknown";
            contentType = "Unknown";
        }
        return new SearchResult(idStr, name, contentType);
    }

    @Override
    public boolean equals(Object obj) {

        SearchResult searchResult = (SearchResult) obj;
        return name.equals(searchResult.name) && id.equals(searchResult.id) && contentType.equals(searchResult.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType);
    }
}
