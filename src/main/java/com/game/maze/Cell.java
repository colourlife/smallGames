package com.game.maze;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017-09-11.
 */
public class Cell {
    public int x;
    public int y;
    public Cell parent;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getDistance(Cell rightCell){
        return Math.abs(this.x - rightCell.x) + Math.abs(this.y - rightCell.y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Cell) {
            Cell cell = (Cell) obj;
            return this.x == cell.x && this.y == cell.y;
        }
        return false;
    }

    /**
     * 获取路径长度
     * @return
     */
    public int getTraceCount(){
        int count = 0;
        Cell parent = this.parent;
        while(parent != null){
            count ++;
            parent = parent.parent;
        }
        return count;
    }

    /**
     * 打印路径
     * @param reverse 是否逆向打印路径（从终点到起点）
     * @return
     */
    public String printTrace(boolean reverse){
        StringBuilder resultStr = new StringBuilder();
        Cell parent = this.parent;
        if(reverse){
            resultStr.append(this.toString());
            while(parent != null){
                resultStr.append(" => ").append(parent.toString());
                parent = parent.parent;
            }
        }else{
            List<Cell> traceList = new ArrayList<Cell>();
            traceList.add(this);
            while(parent != null){
                traceList.add(parent);
                parent = parent.parent;
            }
            for(int i=traceList.size()-1; i>=0; i--){
                if(i < traceList.size() - 1){
                    resultStr.append(" => ");
                }
                resultStr.append(traceList.get(i).toString());
            }
        }
        return resultStr.toString();
    }

    @Override
    public String toString(){
        return String.format("Cell[%d, %d]", x, y);
    }
}
