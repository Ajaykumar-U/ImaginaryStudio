package com.example.pixabaystudio.model;

public class ImagesPojo {
    private String tags;
    private String webformatURL;

    public String getTags() {
        return tags;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public ImagesPojo(String tags, String webformatURL) {
        this.tags = tags;
        this.webformatURL = webformatURL;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }
}
