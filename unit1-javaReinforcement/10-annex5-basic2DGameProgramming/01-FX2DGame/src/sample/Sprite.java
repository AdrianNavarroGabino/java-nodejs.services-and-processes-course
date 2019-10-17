// Adrián Navarro Gabino

package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private double x,y,width,height;
    private Image img;

    public Sprite(double x, double y, double height, double width, Image img)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void draw(GraphicsContext gc)
    {
        gc.drawImage(img, x, y);
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(x, y, width, height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}
