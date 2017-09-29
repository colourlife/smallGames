package com.game.maze;

import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * used to parse *.map file to a maze instance to resolve<br/>
 * Created by lee on 2017-09-21.
 */
public class MapParser {

    private static final char CHAR_START    = 'S';
    private static final char CHAR_END      = 'E';
    private static final char CHAR_OBSTACLE = '#';
    private static final char CHAR_BLANK    = ' ';
    private static final char CHAR_TRACE    = '|';

    /**
     * 解析map文件中的单个字符
     * @param maze 迷宫对象
     * @param ch 带解析字符
     * @param i 行号（从0开始）
     * @param j 列好（从0开始
     */
    private static void parseChar(Maze maze, char ch, int i, int j){
        switch(ch){
            case CHAR_OBSTACLE:
                maze.addObstacle(j+1, i+1);
                break;
            case CHAR_START:
                if(maze.getStart() == null) {
                    maze.initStart(j + 1, i + 1);
                }
                break;
            case CHAR_END:
                if(maze.getEnd() == null) {
                    maze.initEnd(j + 1, i + 1);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 解析迷宫map文件内容
     * @param mapLines 所有行内容集合
     * @return
     */
    public static Maze parse(List<String> mapLines){
        if(CollectionUtils.isEmpty(mapLines)) return null;
        int lastNoEmptyIndex = 0;
        int maxLength = 0;
        for(int i=0; i<mapLines.size(); i++){
            int len = mapLines.get(i).length();
            if(len > 0){
                lastNoEmptyIndex = i;
                if(maxLength < len){
                    maxLength = len;
                }
            }
        }
        Maze maze = new Maze(maxLength, lastNoEmptyIndex + 1);
        for(int i=0; i<maze.getHeight(); i++){
            String line = mapLines.get(i);
            for(int j=0; j<line.length(); j++){
                parseChar(maze, line.charAt(j), i, j);
            }
        }
        return maze;
    }

    /**
     * 解析迷宫map文件流
     * @param mapStream
     * @return
     */
    public static Maze parse(InputStream mapStream){
        if(mapStream == null) return null;
        List<String> lineList = new ArrayList<String>();
        Reader reader = new InputStreamReader(mapStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lineList.add(line);
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally{
            if(bufferedReader != null)
                try{ bufferedReader.close(); }catch(IOException e){ throw new RuntimeException(e); }
            if(reader != null)
                try{ reader.close(); }catch(IOException e){ throw new RuntimeException(e); }
        }
        return parse(lineList);
    }

    /**
     * 解析迷宫map文件
     * @param mapFile
     * @return
     */
    public static Maze parse(File mapFile){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(mapFile);
            return parse(fileInputStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally{
            if(fileInputStream != null)
                try{ fileInputStream.close(); }catch(IOException e){ throw new RuntimeException(e); }
        }
    }

    public static String printMap(Maze maze, Cell trace){
        StringBuilder result = new StringBuilder(maze.getWidth() * maze.getHeight());
        for(int i=0; i<maze.getHeight(); i++){
            for(int j=0; j<maze.getWidth(); j++){
                Cell curCell = new Cell(j, i);
                if(maze.inObstacles(curCell)){
                    result.append(CHAR_OBSTACLE);
                }else if(curCell.equals(maze.getStart())){
                    result.append(CHAR_START);
                }else if(curCell.equals(maze.getEnd())){
                    result.append(CHAR_END);
                }else{
                    Cell tmpCell = trace;
                    while(tmpCell != null){
                        if(tmpCell.equals(curCell)){
                            result.append(CHAR_TRACE);
                            break;
                        }
                        tmpCell = tmpCell.parent;
                    }
                    if(tmpCell == null){
                        result.append(CHAR_BLANK);
                    }
                }
            }
            if(i < maze.getHeight() - 1){
                result.append('\n');
            }
        }
        return result.toString();
    }

}
