package com.game.shudu;

import java.io.*;
import java.lang.Math;

public class ShuDuParser {

    private static final char CHAR_BORDER = '#';
    private static final char CHAR_SEPARATOR_X = '-';
    private static final char CHAR_SEPARATOR_Y = '|';

    public static ShuDu parse(String content) {
        String[] lines = content.split("\r?\n");
        ShuDu result = new ShuDu((int) Math.sqrt((lines.length - 1) / 2));
        int row = 0;
        for(int i=1; i<lines.length; i+=2) {
            int column = 0;
            for(int j=0; j<lines[i].length(); ) {
                int pos_left = j;
                int pos_right = findSeparator(lines[i], j+1);
                if(pos_right == -1) break;
                String numStr = lines[i].substring(pos_left + 1, pos_right).trim();
                if(!numStr.isEmpty()) {
                    result.addFixedValue(row + 1, column + 1, Integer.parseInt(numStr));
                }
                column ++;
                j = pos_right;
            }
            row ++;
        }
        return result;
    }

    private static int findSeparator(String str, int start) {
        for(int i=start; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == CHAR_BORDER || ch == CHAR_SEPARATOR_X || ch == CHAR_SEPARATOR_Y) {
                return i;
            }
        }
        return -1;
    }

    public static ShuDu parse(InputStream inputStream) {
        if(inputStream == null) return null;
        StringBuffer content = new StringBuffer();
        byte[] buffer = new byte[10];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
            while(bufferedInputStream.read(buffer) != -1) {
                content.append(new String(buffer));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{ bufferedInputStream.close(); }catch(IOException e){ throw new RuntimeException(e); }
        }
        return parse(content.toString());
    }

    public static ShuDu parse(File file) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            return parse(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            if(fileInputStream != null)
                try{ fileInputStream.close(); }catch(IOException e){ throw new RuntimeException(e); }
        }
    }
}
