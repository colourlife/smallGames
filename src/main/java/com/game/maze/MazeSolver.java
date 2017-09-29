package com.game.maze;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lee on 2017-09-11.
 */
public class MazeSolver {

    private Maze maze;
    private List<Cell> openList;
    private List<Cell> closeList;

    private Logger logger = LoggerFactory.getLogger(MazeSolver.class);

    public MazeSolver(Maze maze){
        this.maze = maze;
        this.openList = new LinkedList<Cell>();
        this.closeList = new LinkedList<Cell>();
    }

    private int getFValue(Cell cell){
        // 获取从起点到该点经过的步数
        int gValue = 0;
        Cell cellCopy = cell;
        while(cellCopy.parent != null){
            gValue ++;
            cellCopy = cellCopy.parent;
        }
        Assert.isTrue(cellCopy.equals(maze.getStart()), "cell must from closeList");
        // F = G + H
        return gValue + cell.getDistance(maze.getEnd());
    }

    private Cell findMinCell(){
        if(CollectionUtils.isEmpty(this.openList)){
            return null;
        }
        Cell retCell = null;
        int min = Integer.MAX_VALUE;
        for(Cell cell : this.openList){
            int fValue = this.getFValue(cell);
            if(min > fValue){
                min = fValue;
                retCell = cell;
            }
        }
        return retCell;
    }

    private List<Cell> findNeighbors(Cell cell){
        List<Cell> retList = new ArrayList<Cell>();
        if(cell.x > 0){
            Cell leftCell = new Cell(cell.x - 1, cell.y);
            retList.add(leftCell);
        }
        if(cell.x < maze.getWidth() - 1){
            Cell rightCell = new Cell(cell.x + 1, cell.y);
            retList.add(rightCell);
        }
        if(cell.y > 0){
            Cell bottomCell = new Cell(cell.x, cell.y - 1);
            retList.add(bottomCell);
        }
        if(cell.y < maze.getHeight() - 1){
            Cell topCell = new Cell(cell.x, cell.y + 1);
            retList.add(topCell);
        }
        for(int i=0; i<retList.size(); i++){
            if(this.closeList.contains(retList.get(i)) || maze.inObstacles(retList.get(i))){
                retList.remove(i--);
            }
        }
        return retList;
    }

    private void markAndInvolve(Cell current, Cell neighbor){
        neighbor.parent = current;
        this.openList.add(neighbor);
    }

    private Cell aStartSearch(Cell start, Cell end){
        openList.add(start);
        while(openList.size() > 0){
            Cell current = this.findMinCell();
            openList.remove(current);
            closeList.add(current);
            List<Cell> neighbors = this.findNeighbors(current);
            for(Cell cell : neighbors){
                if(!openList.contains(cell)){
                    // 标记父节点并加入openList
                    this.markAndInvolve(current, cell);
                }
            }
            int findPos = openList.indexOf(end);
            if(findPos != -1){
                return openList.get(findPos);
            }
        }
        return null;
    }

    /**
     * 解决并返回从起点到终点的路径（找不到路径时返回null）
     * @return
     */
    public Cell solve(){
        return this.aStartSearch(maze.getStart(), maze.getEnd());
    }

}
