package com.game.maze;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017-09-11.
 */
public class Maze {

    private int width;
    private int height;
    private Cell start;  // 迷宫起点
    private Cell end;   // 迷宫终点
    private List<Cell> obstacles;  // 障碍列表

    /**
     * @param width 迷宫宽度
     * @param height 迷宫高度
     */
    public Maze(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    /**
     * 初始化起点
     * @param xPos
     * @param yPos
     */
    public void initStart(int xPos, int yPos){
        if(xPos <= 0 || xPos > width || yPos <= 0 || yPos > height){
            throw new RuntimeException("not a valid start point");
        }
        this.start = new Cell(xPos - 1, yPos - 1);
    }

    /**
     * 初始化终点
     * @param xPos
     * @param yPos
     */
    public void initEnd(int xPos, int yPos){
        if(xPos <= 0 || xPos > width || yPos <= 0 || yPos > height){
            throw new RuntimeException("not a valid end point");
        }
        this.end = new Cell(xPos - 1, yPos - 1);
    }

    /**
     * 添加障碍格子
     * @param xPos
     * @param yPos
     */
    public void addObstacle(int xPos, int yPos){
        if(this.obstacles == null){
            this.obstacles = new ArrayList<Cell>();
        }
        if(xPos <= 0 || xPos > width || yPos <= 0 || yPos > height){
            throw new RuntimeException("not a valid point");
        }
        Cell obstacle = new Cell(xPos - 1, yPos - 1);
        if(!this.obstacles.contains(obstacle)){
            this.obstacles.add(obstacle);
        }
    }

    public boolean inObstacles(Cell cell){
        if(CollectionUtils.isEmpty(this.obstacles)){
            return false;
        }
        return this.obstacles.contains(cell);
    }
}
