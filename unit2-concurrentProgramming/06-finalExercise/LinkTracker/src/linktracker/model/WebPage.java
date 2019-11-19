// Adrián Navarro Gabino

package linktracker.model;

import java.util.List;

/**
 *<h1>WebPage</h1>
 * Class to define web pages with their name, url and a list with the links
 * in the web page.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class WebPage
{
    private String name;
    private String url;
    private List<String> links;

    /**
     * Performs the initialization to the name and url.
     * @param name Web page name
     * @param url Web page url
     */
    public WebPage(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    /**
     * Gets the name.
     * @return Web page name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the url.
     * @return Web page url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the links list.
     * @return Links in the page
     */
    public List<String> getLinks() {
        return links;
    }

    /**
     * Sets the name.
     * @param name Web page name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the url.
     * @param url Web page url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the links list.
     * @param links Links in the web page
     */
    public void setLinks(List<String> links) {
        this.links = links;
    }

    /**
     * Overrides toString method to get the web page name.
     * @return Web page name
     */
    @Override
    public String toString() {
        return name;
    }
}
