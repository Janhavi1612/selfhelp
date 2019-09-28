package com.janhavi.android.self_help;

public class Upload {
    private String content;
    private String uri;
    private boolean imagePresent;

    public Upload(){
        content = "";
        uri = "";
        imagePresent = true;
    }

    public Upload(String content, String uri){
        if(uri.trim().equals("")){
            imagePresent = false;
        }

        this.content= content;
        this.uri= uri;
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
