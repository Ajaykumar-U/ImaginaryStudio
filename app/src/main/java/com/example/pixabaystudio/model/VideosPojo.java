package com.example.pixabaystudio.model;

public class VideosPojo {
    private String tags;
    private String url;

    public String getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VideosPojo(String tags, String url) {
        this.tags = tags;
        this.url = url;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


}
