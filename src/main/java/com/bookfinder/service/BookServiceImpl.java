package com.bookfinder.service;

import com.bookfinder.entity.Book;
import com.bookfinder.response.GoodreadsResponse;
import com.bookfinder.exception.BookSearchException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Value("${goodreads.url}")
    private String url;
    @Value("${goodreads.key}")
    private String key;

    public List<Book> seach(String keyword, SearchType searchType, Integer page) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            URI uri = new URIBuilder(request.getURI())
                    .addParameter("key", key)
                    .addParameter("q", keyword)
                    .addParameter("search[field]", searchType == null ? null : searchType.name().toLowerCase())
                    .addParameter("page", String.valueOf(page))
                    .build();
            request.setURI(uri);
            CloseableHttpResponse response = httpClient.execute(request);
            GoodreadsResponse goodreadsResponse = unmarshall(response.getEntity().getContent());
            return Optional.ofNullable(goodreadsResponse)
                    .map(GoodreadsResponse::getSearch)
                    .map(GoodreadsResponse.Search::getResults)
                    .map(GoodreadsResponse.Results::getWorks)
                    .map(works -> works.stream().map(GoodreadsResponse.Work::getBook).collect(Collectors.toList()))
                    .orElse(new ArrayList<>());
        } catch (JAXBException e) {
            log.error(e.getMessage());
            throw new BookSearchException(500, "Failed to retrieve data from response");
        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage());
            throw new BookSearchException(503, "Unable to get response from Goodreads");
        }
    }

    private GoodreadsResponse unmarshall(InputStream stream) throws JAXBException {

        return (GoodreadsResponse) JAXBContext.newInstance(GoodreadsResponse.class)
                .createUnmarshaller()
                .unmarshal(stream);
    }
}
