package com.game.maze;

import org.junit.Test;

/**
 * Created by lee on 2017-09-28.
 */
public class MazeSolverTest {

    @Test
    public void testSolve(){
        Maze maze = MapParser.parse(MazeSolverTest.class.getClassLoader().getResourceAsStream("maze/maze-2.map"));
        MazeSolver mazeSolver = new MazeSolver(maze);
        Cell trace = mazeSolver.solve();
        if(trace == null) {
            System.out.println("no trace founded...");
        } else {
            // 打印路径长度
            System.out.println("trace count is: " + trace.getTraceCount());
            // 打印路径节点
            System.out.println("trace is:(from end to start)\n" + trace.printTrace(false));
            // 将路径打印在地图中
            System.out.println("trace shown in map:\n" + MapParser.printMap(maze, trace));
        }
    }
}
