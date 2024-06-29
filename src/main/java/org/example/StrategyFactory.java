package org.example;
public class StrategyFactory {

    public static MovementStrategy getMovementStrategy(String startegy)
    {
        MovementStrategy movementStrategy;

        if(startegy.equalsIgnoreCase("sum"))
        {
            movementStrategy = new Sum();
        }
        else if(startegy.equalsIgnoreCase("max"))
        {
            movementStrategy = new Max();
        }
        else if(startegy.equalsIgnoreCase("min"))
        {
            movementStrategy = new Min();
        }
        else {
            throw new IllegalArgumentException("Invalid");
        }
        return movementStrategy;
    }
}
