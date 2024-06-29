package org.example;
import java.util.Random;

public class Max implements MovementStrategy{

    Random random;

    Max()
    {
        this.random = new Random();
    }
    @Override
    public int rollDice(int diceCount,int max)
    {
        int result = 0;
        int currDiceCount = 0;

        while(currDiceCount < diceCount)
        {
            int randNum = random.nextInt()%max;
            while(randNum <= 0)
            {
                randNum = random.nextInt()%max;
            }
            result = Math.max(result,randNum);
            currDiceCount++;
        }
        return result;
    }
}
