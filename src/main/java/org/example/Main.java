package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try{
            GameConfiguration configuration = GameConfiguration.loadFromJsonFile("D:\\SnakeAndLadder\\src\\main\\java\\org\\example\\input.json");
            Dice dice = new Dice(configuration.numberOfDice,configuration.movementStrategy);
            List<Snake> snakes = new ArrayList<>();
            for (int i = 0; i < configuration.numberOfSnakes; i++) {
                snakes.add(new Snake(configuration.snakePositions.get(i).get(0),configuration.snakePositions.get(i).get(1)));
            }
            List<Ladder> ladders = new ArrayList<>();
            for (int i = 0; i < configuration.numberOfLadders; i++) {
                ladders.add(new Ladder(configuration.ladderPositions.get(i).get(0),configuration.ladderPositions.get(i).get(1)));
            }
            List<Crocodile> crocodiles = new ArrayList<>();
            for (int i = 0; i < configuration.numberOfCrocodiles; i++) {
                crocodiles.add(new Crocodile(configuration.crocodilePositions.get(i)));
            }
            List<Mine> mines = new ArrayList<>();
            for (int i = 0; i < configuration.numberOfMines; i++) {
                mines.add(new Mine(configuration.minePositions.get(i)));
            }
            Game game = new Game(dice, snakes, ladders,crocodiles,mines,configuration.boardSize);
            for (int i = 0; i < configuration.numberOfPlayers; i++) {
                game.addPlayer(new Player((String)configuration.playersNameAndStartPos.get(i).get(0),(int)configuration.playersNameAndStartPos.get(i).get(1)));
            }
            game.startGame();
        }
        catch(IOException exception)
        {
            System.out.println("Error loading game configuration: "+exception.getMessage());
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println("Invalid input: "+exception.getMessage());
        }
        //enterInputManually(); //please uncomment this line to enter the input manually
    }

    private static void enterInputManually()
    {
        Scanner sc = new Scanner(System.in);
        int boardSize = getBoardSize(sc);
        int n = getNumberOfDice(sc);
        String strategy = getStrategy(sc);
        Dice dice = new Dice(n,strategy);

        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();
        List<Crocodile> crocodiles = new ArrayList<>();
        List<Mine> mines = new ArrayList<>();

        int s = getSnakesCount(sc);
        for (int i = 0; i < s; i++) {
            Pair p = getSnakesStartAndEndPos(sc,boardSize);
            snakes.add(new Snake((int)p.x, (int)p.y));
        }

        int l = getLaddersCount(sc);
        for (int i = 0; i < l; i++) {
            Pair p = getLaddersStartAndEndPos(sc,boardSize);
            ladders.add(new Ladder((int)p.x, (int)p.y));
        }

        int c = getCrocodilesCount(sc);
        for(int i=0;i<c;i++)
        {
            crocodiles.add(new Crocodile(getCrocodilesPos(sc,boardSize)));
        }

        int m = getMinesCount(sc);
        for(int i=0;i<m;i++)
        {
            mines.add(new Mine(getMinesPos(sc,boardSize)));
        }

        int p = getPlayersCount(sc);
        Game game = new Game(dice, snakes, ladders,crocodiles,mines,boardSize);
        for (int i = 0; i < p; i++) {
            Pair temp = getPlayersNameAndStartPos(sc,boardSize);
            game.addPlayer(new Player((String)temp.x,(int)temp.y));
        }
        game.startGame();
    }
    private static int getBoardSize(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the board size: ");
                int value = sc.nextInt();
                if(value <= 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getNumberOfDice(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the number of die: ");
                int value = sc.nextInt();
                if(value <= 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static String getStrategy(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the movement strategy you want to play with. Eg, sum or max or min: ");
                String input = sc.next();
                if(!input.equalsIgnoreCase("sum") && !input.equalsIgnoreCase("max") && !input.equalsIgnoreCase("min"))
                {
                    throw new Exception();
                }
                return input;
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getSnakesCount(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the number of snakes: ");
                int value = sc.nextInt();
                if(value < 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static Pair getSnakesStartAndEndPos(Scanner sc,int boardSize)
    {
        while(true)
        {
            try{
                System.out.println("Enter the snake start and end positions: ");
                int start = sc.nextInt();
                int end = sc.nextInt();

                if(start <= 1 || end <= 0 || start >= boardSize*boardSize || end >= boardSize*boardSize || start <= end)
                {
                    throw new Exception();
                }
                else{
                    return new Pair(start,end);
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static Pair getLaddersStartAndEndPos(Scanner sc,int boardSize)
    {
        while(true)
        {
            try{
                System.out.println("Enter the ladder start and end positions: ");
                int start = sc.nextInt();
                int end = sc.nextInt();

                if(start <= 1 || end <= 1 || start >= boardSize*boardSize || end > boardSize*boardSize || start >= end)
                {
                    throw new Exception();
                }
                else{
                    return new Pair(start,end);
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getLaddersCount(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the no of ladders: ");
                int value = sc.nextInt();
                if(value < 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getPlayersCount(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the number of players: ");
                int value = sc.nextInt();
                if(value <= 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static Pair getPlayersNameAndStartPos(Scanner sc,int boardSize)
    {
        while(true)
        {
            try{
                System.out.println("Enter the player name and start pos: ");
                String name = sc.next();
                if(name.isEmpty() || !name.matches("[a-zA-Z]+"))
                {
                    throw new Exception();
                }
                int value = sc.nextInt();
                if(value <= 0 || value >= boardSize*boardSize)
                {
                    throw new Exception();
                }
                else{
                    return new Pair(name,value);
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getCrocodilesCount(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the no of crocodiles: ");
                int value = sc.nextInt();
                if(value < 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getCrocodilesPos(Scanner sc,int boardSize)
    {
        while(true)
        {
            try{
                System.out.println("Enter the crocodile position: ");
                int value = sc.nextInt();
                if(value <= 1 || value >= boardSize*boardSize)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getMinesCount(Scanner sc)
    {
        while(true)
        {
            try{
                System.out.println("Enter the no of mines: ");
                int value = sc.nextInt();
                if(value < 0)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }

    private static int getMinesPos(Scanner sc,int boardSize)
    {
        while(true)
        {
            try{
                System.out.println("Enter the mine position: ");
                int value = sc.nextInt();
                if(value <= 1 || value >= boardSize*boardSize)
                {
                    throw new Exception();
                }
                else{
                    return value;
                }
            }
            catch(Exception exception)
            {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }
    }
}