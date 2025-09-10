package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.Exception.NameIsBlankException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;


    @Test
    void testSearch_NoObjects() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> result = searchService.search("яблоки");
        assertTrue(result.isEmpty(), "Результат должен быть пустым, если объекты отсутствуют");
    }


    @Test
    void testSearch_NoMatchingObjects() throws NameIsBlankException {
        List<Searchable> objects = new ArrayList<>();
        objects.add(new SimpleProduct(UUID.randomUUID(), "Банан", 10));
        objects.add(new Article(UUID.randomUUID(), "Помидор", "Красные"));

        when(storageService.getAllSearchables()).thenReturn(objects);

        Collection<SearchResult> result = searchService.search("яблоки");
        assertTrue(result.isEmpty(), "Результат должен быть пустым, если подходящих объектов нет");
    }


    @Test
    void testSearch_FoundMatchingObject() throws NameIsBlankException {
        UUID productId = UUID.randomUUID();
        Product apple = new SimpleProduct(productId, "Яблоки", 25);
        List<Searchable> objects = new ArrayList<>();
        objects.add(apple);
        objects.add(new Article(UUID.randomUUID(), "Семечки", "Соленые"));

        when(storageService.getAllSearchables()).thenReturn(objects);

        Collection<SearchResult> result = searchService.search("яблоки");
        assertEquals(1, result.size(), "Должен быть найден один объект");
        assertTrue(result.stream().anyMatch(r -> r.getName().equals("Яблоки")));
        SearchResult searchResult = result.iterator().next();

        assertEquals("Яблоки", searchResult.getName());
        assertEquals("PRODUCT", searchResult.getContentType());
        assertEquals(productId.toString(), searchResult.getId());

        SearchResult expectedResult = new SearchResult(
                productId.toString(),
                "Яблоки",
                "PRODUCT"
        );
        assertEquals(expectedResult, searchResult);
    }


    @Test
    void testSearch_PartialMatch() throws NameIsBlankException {
        UUID productId = UUID.randomUUID();
        Product apple = new SimpleProduct(productId, "Зеленые яблоки", 5);
        List<Searchable> objects = new ArrayList<>();
        objects.add(apple);
        when(storageService.getAllSearchables()).thenReturn(objects);

        Collection<SearchResult> result = searchService.search("яблоки");
        assertEquals(1, result.size(), "Должен быть найден один объект");
        SearchResult expected = new SearchResult(productId.toString(),"Зеленые яблоки", "PRODUCT");


        assertTrue(result.contains(expected), "Результат должен содержать ожидаемый SearchResult");

    }
}