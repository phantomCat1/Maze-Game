/**
 * This class is responsible for checking collision of the player
 * with different elements on the map:
 * Walls
 * Coins
 * Trophy, which is the end of the maze.
 */
public class CollisionChecker {
    Maze maze;

    /**
     * This is the constructor of the class.
     * It makes an instance of the Maze class, meaning that
     * it has access to the Player and MAzeCreator class, for which collision
     * is needed.
     */
    public CollisionChecker(Maze board) {
        this.maze = board;

    }

    /**
     * This method checks the collision of the player with elements in the maze.
     */
    public void checkCollision(Player player) {
        // HitBox rectangle coordinates of the character sprite
        int hitBoxLeftX = player.posX + player.solidArea.x;
        int hitBoxRightX = player.posX + player.solidArea.x + player.solidArea.width;
        int hitBoxTopY = player.posY + player.solidArea.y;
        int hitBoxBottomY = player.posY + player.solidArea.y + player.solidArea.height;

        // Row and Column of the HitBox coordinates in the mazeGrid 2D array
        // Because our maze is created based on a 2D array, we need to know the position
        // of the player relative to this array, meaning the row and column of his HitBox
        int hitBoxLeftCol = hitBoxLeftX / maze.tileSize;
        int hitBoxRightCol = hitBoxRightX / maze.tileSize;
        int hitBoxTopRow = hitBoxTopY / maze.tileSize;
        int hitBoxBottomRow = hitBoxBottomY / maze.tileSize;

        // Two variables that represent the two intersected tiles in the direction of 
        // movement based on their values in the mazeGrid array
        // They take one of the values from the mazeGrid array: 0, 2, 4, 8, 
        // depending on the direction of movement of the player and the coordinates
        // of the HitBox in relation to the mazeGrid array.
        int tile1;
        int tile2;

        // The player moves only in one direction at most when he presses a key
        // Therefore, we have to check the collision of only the 2 corners of the HitBox
        // in that direction
        // We do that by predicting where he'll be when the key is pressed and if 
        // his future position (of the HitBox) has row and column of an element in the
        // mazeGrid array that is supposed to block his path, give him coins or win, then 
        // something happens 

        //The following code is very much the same for all w, a, s, d keys.
        // The only thing that differs is the values of the two tiles, since they
        // depend on the direction of movement.
        if (player.key.sKey) {
            hitBoxBottomRow = (hitBoxBottomY + player.speed) / maze.tileSize;
            tile1 = maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxLeftCol];
            tile2 = maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxRightCol];
            // If the element from the array is 2, then that's a wall and 
            // collisionOn is set to true, meaning in the Player class, the player won't
            // move.
            if (tile1 == 2 || tile2 == 2) {
                player.collisionOn = true;
            }
            // If the element from the array is 4, the coinTouched variable from the Player class
            // is set to true, meaning the player will collect a coin and the value of this row and
            // column in the array is set to 0, meaning the coin tile is replaced with a grass tile 
            //on the map.
            if (tile1 == 4 && tile2 == 4) {
                player.coinTouched = true;
                maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxLeftCol] = 0;
                
            }
            // If the element from the array is 8, then the reachedEnd variable is set to true
            // meaning that the player has reached the end of the maze.
            if (tile1 == 8 || tile2 == 8) {
                player.reachedEnd = true;
            }

        }

        if (player.key.wKey) {
            hitBoxTopRow = (hitBoxTopY - player.speed) / maze.tileSize;
            tile1 = maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxLeftCol];
            tile2 = maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxRightCol];
            if (tile1 == 2 || tile2 == 2) {
                player.collisionOn = true;
            }
            if (tile1 == 4 && tile2 == 4) {
                player.coinTouched = true;
                maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxLeftCol] = 0;
            }
            if (tile1 == 8 || tile2 == 8) {
                player.reachedEnd = true;
            }

        }

        if (player.key.aKey) {
            hitBoxLeftCol = (hitBoxLeftX - player.speed) / maze.tileSize;
            tile1 = maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxLeftCol];
            tile2 = maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxLeftCol];
            if (tile1 == 2 || tile2 == 2) {
                player.collisionOn = true;
            }
            if (tile1 == 4 && tile2 == 4) {
                player.coinTouched = true;
                maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxLeftCol] = 0;
                
            }
            if (tile1 == 8 || tile2 == 8) {
                player.reachedEnd = true;
            }

        }

        if (player.key.dKey) {
            hitBoxRightCol = (hitBoxRightX + player.speed) / maze.tileSize;
            tile1 = maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxRightCol];
            tile2 = maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxRightCol];
            if (tile1 == 2 || tile2 == 2) {
                player.collisionOn = true;
            }
            if (tile1 == 4 && tile2 == 4) {
                player.coinTouched = true;
                maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxRightCol] = 0;
                
            }
            if (tile1 == 8 || tile2 == 8) {
                player.reachedEnd = true;
            }

        }

    }
    
}
