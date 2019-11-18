package linktracker.model;

import java.util.List;

public class WebPage
{
    String name;
    String url;
    List<String> links;

    public WebPage(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
