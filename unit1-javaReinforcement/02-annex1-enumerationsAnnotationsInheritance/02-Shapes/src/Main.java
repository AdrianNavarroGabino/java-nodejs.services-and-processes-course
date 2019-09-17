// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        IShape s = new Rectangle(4, 2);
        Circle c = new Circle(5);
        Triangle t = new Triangle(4, 2);
        IShape square = new IShape() {
            double side = 3;

            @Override
            public double getArea() {
                return side * side;
            }
        };

        System.out.printf("Shape area: %.2f\n", s.getArea());
        System.out.printf("Circle area: %.2f\n", c.getArea());
        System.out.printf("Triangle area: %.2f\n", t.getArea());
        System.out.printf("Square area: %.2f\n",square.getArea());

        ShapeComparator<Triangle, Rectangle> triRecComp =
                new ShapeComparator<>(
                        new Triangle(9.65, 5.5), new Rectangle(6.7,5));
        triRecComp.compare();
        ShapeComparator<Rectangle, Circle> recCirComp =
                new ShapeComparator<>(
                        new Rectangle(9.65, 5.5), new Circle(9));
        recCirComp.compare();
    }
}
