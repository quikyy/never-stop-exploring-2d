package utils;

import java.io.*;

public class MapLoader {
    final static private String mapPath = "src/resources/map/map.txt";

    public static int[][] loadMapFromFile(int maxWorldColumn) {
        int[][] arr = new int[32][40];
        int row = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(mapPath)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] ids = line.split(" ");
                for (int i = 0; i < maxWorldColumn; i++) {
                    arr[row][i] = Integer.parseInt(ids[i]);
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }

}
