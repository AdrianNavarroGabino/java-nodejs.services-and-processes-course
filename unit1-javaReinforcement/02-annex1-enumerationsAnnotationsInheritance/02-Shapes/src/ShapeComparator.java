// Adrián Navarro Gabino

public class ShapeComparator<T extends IShape, U extends IShape>
{
    private T shape1;
    private U shape2;
    ShapeComparator(T shape1, U shape2)
    {
        this.shape1 = shape1;
        this.shape2 = shape2;
    }
    public T getShape1()
    {
        return shape1;
    }
    public void setShape1(T shape1)
    {
        this.shape1 = shape1;
    }
    public U getShape2()
    {
        return shape2;
    }
    public void setShape2(U shape2)
    {
        this.shape2 = shape2;
    }
    public void compare()
    {
        double a1 = shape1.getArea();
        double a2 = shape2.getArea();
        if(a1 > a2) {
            System.out.printf("Shape 1 wins: %.2f vs %.2f\n", a1, a2);
        } else if(a2 > a1) {
            System.out.printf("Shape 2 wins: %.2f vs %.2f\n", a2, a1);
        } else {
            System.out.printf("Tie: %.2f\n", a1);
        }
    }
}
