package org.example;

public class Dice {

    int diceCount;

    int max = 6;

    MovementStrategy movementStrategy;

    public Dice(int diceCount,String strategy)
    {
        this.diceCount = diceCount;
        movementStrategy = StrategyFactory.getMovementStrategy(strategy);
    }

    public int rollDice()
    {
        return movementStrategy.rollDice(this.diceCount,this.max);
    }
}
