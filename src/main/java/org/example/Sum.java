package org.example;
import java.util.Random;

public class Sum implements MovementStrategy{
    Random random;
    Sum()
    {
        random = new Random();
    }
    @Override
    public int rollDice(int diceCount, int max)
    {
        int totalSum = 0;
        int currDiceCount = 0;
        while(currDiceCount < diceCount)
        {
            int randNum = random.nextInt()%max;
            while(randNum <= 0)
            {
                randNum = random.nextInt()%max;
            }
            totalSum += randNum;
            currDiceCount++;
        }
        return totalSum;
    }
}
