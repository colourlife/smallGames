package com.game.shudu;

import java.util.*;

/**
 * Created by lee on 2017-08-14.
 */
public class Cell {

    public int x;
    public int y;
    private Integer value;

    private List<Integer> validList;
    private int currentPos;
    public boolean isProceed;
    public boolean isFixed; //是否为固定值（不可变更）

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.isFixed = false;
        this.currentPos = -1;
        this.isProceed = false;
    }

    public Cell(int x, int y, int fixedValue){
        this.x = x;
        this.y = y;
        this.value = fixedValue;
        this.isFixed = true;
        this.isProceed = true;
    }

    public Integer getValue() {
        return value;
    }

    public void setValidList(List<Integer> validList) {
        if(!this.isFixed) {
            if(this.validList == null){
                this.validList = new ArrayList<Integer>();
            }else if(!this.validList.isEmpty()){
                this.validList.clear();
            }
            this.validList.addAll(validList);
            // 打乱格子有效值的排列排序，便于随机生成结果
            Collections.shuffle(this.validList);
        }
    }

    public void setFixedValue(int value){
        this.value = value;
        this.isFixed = true;
        this.isProceed = true;
    }

    public boolean pickNextValidValue(){
        if(validList == null || currentPos >= validList.size()-1){
            return false;
        }
        this.value = this.validList.get(++currentPos);
        return true;
    }

    public void clear(){
        if(!this.isFixed) {
            this.validList.clear();
            this.currentPos = -1;
            this.value = null;
            this.isProceed = false;
        }
    }
}
