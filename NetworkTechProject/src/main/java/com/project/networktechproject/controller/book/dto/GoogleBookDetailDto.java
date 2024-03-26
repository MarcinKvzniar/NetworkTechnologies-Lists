package com.project.networktechproject.controller.book.dto;

public class GoogleBookDetailDto {
    private String language;
    private int pageCount;
    private String categories;
    private String description;
    private ImageLinks imageLinks;

    public GoogleBookDetailDto() {
    }

    public GoogleBookDetailDto(String language, int pageCount, String categories, String description, ImageLinks imageLinks) {
        this.language = language;
        this.pageCount = pageCount;
        this.categories = categories;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public static class ImageLinks {
        private String thumbnail;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
