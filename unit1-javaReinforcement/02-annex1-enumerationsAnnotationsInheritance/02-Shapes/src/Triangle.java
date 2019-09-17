// Adrián Navarro Gabino

public class Triangle implements IShape
{
    private double height, base;
    public Triangle(double height, double base)
    {
        this.height = height;
        this.base = base;
    }
    @Override
    public double getArea()
    {
        return height * base / 2;
    }
}
