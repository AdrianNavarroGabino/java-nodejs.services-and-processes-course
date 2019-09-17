// Adrián Navarro Gabino

public class Cat extends Animal
{
    public Cat(String name)
    {
        super(name);
    }

    @Override
    public String talk() {
        return "Meooow";
    }
}
