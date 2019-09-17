// Adrián Navarro Gabino

public class Enemy implements Character
{
    @Override
    public boolean isEnemy() {
        return true;
    }

    public void kill()
    {
        System.out.println("Ahhhggg, you killed me, bastard!");
    }
}
