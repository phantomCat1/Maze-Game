import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MazeCreator {
    Maze mazePanel;
    int[][] mazeGrid;
    BufferedImage wallTile;
    BufferedImage grassTile;
    BufferedImage trophyTile;
    BufferedImage coinTile;
    public MazeCreator(Maze mp, int level) {
        this.mazePanel = mp;
        mazeGrid = new int[level][level];

        try {
            wallTile = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\wallTile.png"));
            grassTile = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\grassTile.png"));
            trophyTile = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\trophyTile.png"));
            coinTile = ImageIO.read(new File("C:\\Users\\20232049\\OneDrive - TU Eindhoven\\Documents\\MazeGame\\Character\\coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        createMazeArray();

    }

    public void createMazeArray() {

    }


    public void draw(Graphics2D g) {
        
        
    }
}
