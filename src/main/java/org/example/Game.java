package org.example;
import java.util.*;

public class Game {

    Dice dice;

    Cell[][] cells;

    int boardSize;
    Queue<Player> playersList = new LinkedList<>();
    Player winner;
    public Game(Dice dice, List<Snake> snakeList, List<Ladder> ladderList,List<Crocodile> crocodileList,List<Mine> minesList,int boardSize)
    {
        this.dice = dice;
        this.boardSize = boardSize;
        cells = new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                cells[i][j] = new Cell();
            }
        }

        for(int i=0;i<snakeList.size();i++)
        {
            int boardRow = snakeList.get(i).getStart()/cells.length;
            int boardCol = snakeList.get(i).getStart()%cells.length;
            if(cells[boardRow][boardCol].getSpecialObject() != null)
            {
                System.out.println("specialObject is already present");
            }
            else
            {
                cells[boardRow][boardCol].setSpecialObject(snakeList.get(i));
            }
        }
        for(int i=0;i<ladderList.size();i++)
        {
            int boardRow = ladderList.get(i).getStart()/cells.length;
            int boardCol = ladderList.get(i).getStart()%cells.length;
            if(cells[boardRow][boardCol].getSpecialObject() != null)
            {
                System.out.println("specialObject is already present");
            }
            else {
                cells[boardRow][boardCol].setSpecialObject(ladderList.get(i));
            }
        }
        for(int i=0;i<crocodileList.size();i++)
        {
            int boardRow = crocodileList.get(i).getStart()/cells.length;
            int boardCol = crocodileList.get(i).getStart()%cells.length;
            if(cells[boardRow][boardCol].getSpecialObject() != null)
            {
                System.out.println("specialObject is already present");
            }
            else
            {
                cells[boardRow][boardCol].setSpecialObject(crocodileList.get(i));
            }
        }
        for(int i=0;i<minesList.size();i++)
        {
            int boardRow = minesList.get(i).getStart()/cells.length;
            int boardCol = minesList.get(i).getStart()%cells.length;
            if(cells[boardRow][boardCol].getSpecialObject() != null)
            {
                System.out.println("specialObject is already present");
            }
            else
            {
                cells[boardRow][boardCol].setSpecialObject(minesList.get(i));
            }
        }
        winner = null;
    }

    public void addPlayer(Player player)
    {
        playersList.offer(player);
    }
    public void startGame()
    {
        while(true)
        {
            Player player = playersList.poll();
            if(player.getSkipTurns() > 0)
            {
                player.setSkipTurns(player.getSkipTurns()-1);
                playersList.add(player);
                continue;
            }
            if(winner == null)
            {
                move(player);
                playersList.add(player);
            }
            else
            {
                return;
            }
        }
    }

    private void move(Player player)
    {
        int rollResult = dice.rollDice();
        int prevPos = player.getPos();
        int moveTo = prevPos + rollResult;

        if(moveTo == (boardSize*boardSize))
        {
            System.out.println(player.name+" rolled a "+rollResult+" and moved from "+prevPos+" to "+moveTo);
            player.setPos(moveTo);
            winner = player;
        }
        else if(moveTo > (boardSize*boardSize))
        {
            System.out.println(player.name+" rolled a "+rollResult+". Exceeded the steps. Please stay in the same position");
        }
        else
        {
            Cell cell = getCell(moveTo);
            if(cell.getSpecialObject() != null)
            {
                if(cell.getSpecialObject() instanceof Snake)
                {
                    System.out.println(player.name+" rolled a "+rollResult+" and bitten by snake at "+moveTo+" and moved from " + moveTo +" to "+cell.getSpecialObject().getEnd());
                }
                else if(cell.getSpecialObject() instanceof Ladder)
                {
                    System.out.println(player.name+" rolled a "+rollResult+" and climbed the ladder at "+moveTo+" and moved from " + moveTo +" to "+cell.getSpecialObject().getEnd());
                }
                else if(cell.getSpecialObject() instanceof Crocodile)
                {
                    System.out.println(player.name+" rolled a "+rollResult+" and attacked by crocodile at "+moveTo+" and moved from " + moveTo +" to "+cell.getSpecialObject().getEnd());
                }
                else if(cell.getSpecialObject() instanceof Mine)
                {
                    System.out.println(player.name+" rolled a "+rollResult+" and stepped on a mine at "+moveTo+" and skip 2 turns ");
                    player.setSkipTurns(((Mine) cell.getSpecialObject()).getSkipTurnsCount());
                }
                Cell previousPlayerCell = getCell(prevPos);
                previousPlayerCell.setPlayer(null);
                Cell playerMovedToCell = getCell(cell.getSpecialObject().getEnd());
                playerMovedToCell.setPlayer(player);
                player.setPos(cell.getSpecialObject().getEnd());
            }
            else if(cell.getPlayer() != null)
            {
                Player prevPlayer = cell.getPlayer();
                System.out.println(player.name+" rolled a "+rollResult+" and moved from "+prevPos+" to "+moveTo);
                System.out.println("Found another player at this position "+ prevPlayer.getPos() + ". Player "+ prevPlayer.getName() + " will be moved from " +prevPlayer.getPos()+" to 1.");
                prevPlayer.setPos(1);

                Cell previousPlayerCell = getCell(prevPos);
                previousPlayerCell.setPlayer(null);

                cell.setPlayer(player);
                player.setPos(moveTo);
            }
            else
            {
                System.out.println(player.name+" rolled a "+rollResult+" moved from "+prevPos+" to "+moveTo);
                Cell previousPlayerCell = getCell(prevPos);
                previousPlayerCell.setPlayer(null);

                cell.setPlayer(player);
                player.setPos(moveTo);
            }
        }
    }

    public Cell getCell(int pos)
    {
        int boardRow = pos/cells.length;
        int boardCol = pos%cells.length;
        return cells[boardRow][boardCol];
    }
}
