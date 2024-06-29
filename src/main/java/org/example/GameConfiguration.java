package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameConfiguration {

    public int boardSize;
    public int numberOfDice;

    public String movementStrategy;
    public int numberOfSnakes;

    public List<List<Integer>> snakePositions;
    public int numberOfLadders;
    public List<List<Integer>> ladderPositions;
    public int numberOfCrocodiles;
    public List<Integer> crocodilePositions;
    public int numberOfMines;
    public List<Integer> minePositions;

    public int numberOfPlayers;
    public List<List<Object>> playersNameAndStartPos;

    public static GameConfiguration loadFromJsonFile(String filepath) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filepath),GameConfiguration.class);
    }
}
