// Adrián Navarro Gabino

public class VideoGame
{
    String title;
    float price;

    public VideoGame(String title, float price)
    {
        this.title = title;
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public float getPrice()
    {
        return price;
    }

    public synchronized void addPrice (float p)
    {
        price += p;
    }
}