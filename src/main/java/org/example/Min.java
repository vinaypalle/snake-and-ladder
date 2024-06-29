package org.example;
import java.util.Random;

public class Min implements MovementStrategy{

    Random random;
    public Min()
    {
        random = new Random();
    }

    public int rollDice(int diceCount,int max)
    {
        int result = Integer.MAX_VALUE;
        int currDiceCount = 0;

        while(currDiceCount < diceCount)
        {
            int randNum = random.nextInt()%max;
            while(randNum <= 0)
            {
                randNum = random.nextInt()%max;
            }
            result = Math.min(result,randNum);
            currDiceCount++;
        }
        return result;
    }
}
