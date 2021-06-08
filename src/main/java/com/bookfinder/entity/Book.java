package com.bookfinder.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "best_book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    private int id;
    private Author author;
    private String title;
    @XmlElement(name = "image_url")
    private String imageLink;
    @XmlElement(name = "small_image_url")
    private String smallImageLink;


    @Data
    public static class Author {

        private int id;
        private String name;

    }

}
