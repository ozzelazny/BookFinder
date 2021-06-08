package com.bookfinder.response;

import com.bookfinder.entity.Book;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "GoodreadsResponse")
public class GoodreadsResponse {

    private Search search;

    @Data
    public static class Search {

        private Results results;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Results {

        @XmlElement(name = "work")
        private List<Work> works = new ArrayList<>();
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Work {

        @XmlElement(name = "best_book")
        private Book book;

    }
}

