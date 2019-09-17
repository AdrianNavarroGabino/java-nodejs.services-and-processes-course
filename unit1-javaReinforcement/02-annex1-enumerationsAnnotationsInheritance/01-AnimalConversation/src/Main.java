// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        AnimalConversation<Dog, Cat> animals1 = new AnimalConversation<>(
                new Dog("Rufus"), new Cat("Max"));
        AnimalConversation<Cat, Sheep> animals2 = new AnimalConversation<>(
                new Cat("Chloe"), new Sheep("Missy"));

        animals1.chat();
        animals2.chat();
    }
}
