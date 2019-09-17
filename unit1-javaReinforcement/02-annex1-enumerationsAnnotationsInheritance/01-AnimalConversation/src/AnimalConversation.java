// Adrián Navarro Gabino

public class AnimalConversation<T extends Animal, U extends Animal>
{
    private T animal1;
    private U animal2;

    public AnimalConversation(T animal1, U animal2)
    {
        this.animal1 = animal1;
        this.animal2 = animal2;
    }

    public T getAnimal1() {
        return animal1;
    }

    public U getAnimal2() {
        return animal2;
    }

    public void setAnimal1(T animal1) {
        this.animal1 = animal1;
    }

    public void setAnimal2(U animal2) {
        this.animal2 = animal2;
    }

    public void chat()
    {
        System.out.println(animal1.talk());
        System.out.println(animal2.talk());
    }
}
