import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class MazeCreator {
    Maze maze;
    int[][] mazeGrid;
    BufferedImage wallTile;
    BufferedImage grassTile;
    BufferedImage trophyTile;
    BufferedImage coinTile;
    int actualLevel;
    String levelFile;
    
    public MazeCreator(Maze mp, int level) {
        this.maze = mp;
        actualLevel = level / 5;
        levelFile = "level" + actualLevel + ".txt";
        mazeGrid = new int[level][level];

        try {
            wallTile = ImageIO.read(getClass().getResource("/Character/wallTile.png"));
            grassTile = ImageIO.read(getClass().getResource("/Character/grassTile.png"));
            trophyTile = ImageIO.read(getClass().getResource("Character/trophyTile.png"));
            coinTile = ImageIO.read(getClass().getResource("Character/coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        createMazeArray(levelFile);

    }

    public void createMazeArray(String levelFile) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(levelFile)));
            while (sc.hasNextLine()) {
                for (int i = 0; i < mazeGrid.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        mazeGrid[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void draw(Graphics2D g) {
        int posX = 0;
        int posY = 0;
        for (int i = 0; i < mazeGrid.length; i++) {
            
            for (int j = 0; j < mazeGrid.length; j++) {
                switch (mazeGrid[i][j]) {
                    case 0: g.drawImage(grassTile, posX, posY, maze.tileSize, maze.tileSize, null);
                    break;
                    case 2: g.drawImage(wallTile, posX, posY, maze.tileSize, maze.tileSize, null);
                    break;
                    case 4: g.drawImage(coinTile, posX, posY, maze.tileSize, maze.tileSize, null);
                    break;
                    case 8: g.drawImage(trophyTile, posX, posY, maze.tileSize, maze.tileSize, null);
                    break;
                    default: System.out.println("Tile not printed");
                }
                posX += maze.tileSize;
            }
            posY += maze.tileSize;
            posX = 0;
            
        }
        
        
    }
}
