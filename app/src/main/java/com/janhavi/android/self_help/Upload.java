package com.janhavi.android.self_help;

public class Upload {
    private String content;
    private String uri;
    private boolean imagePresent;
    private String username;

    public Upload(){
        content = "";
        uri = "";
        imagePresent = true;
        username = "janhavi";
    }

    public Upload(String content, String uri){
        if(uri.trim().equals("")){
            imagePresent = false;
        }
        else
            imagePresent = true;

        this.content= content;
        this.uri= uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEmpty(String content){
        if(content.trim().equals("")){
            return true;
        }
        else
            return false;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
