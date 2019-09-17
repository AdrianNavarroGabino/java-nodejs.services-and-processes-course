// Adrián Navarro Gabino

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Character> characters = new ArrayList<Character>();

        for(int i = 0; i < 5; i++)
        {
            characters.add(new Friend());
        }
        for(int i = 0; i < 5; i++)
        {
            characters.add(new Enemy());
        }

        Collections.shuffle(characters);

        for(int i = 0; i < characters.size(); i++)
        {
            if(characters.get(i).isEnemy())
            {
                System.out.println("Character " + i + " is an enemy! Kill it!");
                ((Enemy)characters.get(i)).kill();
            }
            else
            {
                System.out.println("Character " + i + " is a friend! :-)");
            }
        }
    }
}
