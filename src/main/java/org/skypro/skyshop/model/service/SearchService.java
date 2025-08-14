package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        String lowerPattern = pattern.toLowerCase();


        return storageService.getAllSearchables().stream()
                .filter(searchPattern -> {
                    String name = null;
                    if (searchPattern instanceof Article) {
                        name = searchPattern.getproductName();

                    } else if (searchPattern instanceof Product) {
                        name = searchPattern.getproductName();
                    }
                    return name != null && name.toString().toLowerCase().contains(lowerPattern);
                })
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());

    }

}
