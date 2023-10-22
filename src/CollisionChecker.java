public class CollisionChecker {
    Maze maze;

    public CollisionChecker(Maze board) {
        this.maze = board;

    }
    public void checkCollision(Player player) {
        // HitBox rectangle coordinates of the player sprite
        int hitBoxLeftX = player.posX + player.solidArea.x;
        int hitBoxRightX = player.posX + player.solidArea.x + player.solidArea.width;
        int hitBoxTopY = player.posY + player.solidArea.y;
        int hitBoxBottomY = player.posY + player.solidArea.y + player.solidArea.height;

        // Row and Column of the HitBox coordinates in the mazeGrid array
        int hitBoxLeftCol = hitBoxLeftX / maze.tileSize;
        int hitBoxRightCol = hitBoxRightX / maze.tileSize;
        int hitBoxTopRow = hitBoxTopY / maze.tileSize;
        int hitBoxBottomRow = hitBoxBottomY / maze.tileSize;

        // Two variables that represent the two intersected tiles in the direction of 
        // movement based on their values in the mazeGrid array
        int tile1;
        int tile2;

        // The player moves only in one direction at most when he presses a key
        // Therefore, we have to check the collision of only the 2 corners of the HitBox
        // in that direction
        // We do that by predicting where he'll be when the key is pressed and if 
        // his future position (of the HitBox) has row and column of an element in the
        // mazeGrid array that is supposed to block his path, give him coins or win, then 
        // something happens 
        if (player.key.sKey) {
            hitBoxBottomRow = (hitBoxBottomY + player.speed) / maze.tileSize;
            tile1 = maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxLeftCol];
            tile2 = maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxRightCol];
            if (tile1 == 2 || tile2 == 2) {
                player.collisionOn = true;
            }
            if (tile1 == 4 || tile2 == 4) {
                player.coinTouched = true;
                if (tile1 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxLeftCol] = 0;
                } else if (tile2 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxRightCol] = 0;
                }
                
            }
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
            if (tile1 == 4 || tile2 == 4) {
                player.coinTouched = true;
                if (tile1 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxLeftCol] = 0;
                } else if (tile2 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxRightCol] = 0;
                }
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
            if (tile1 == 4 || tile2 == 4) {
                player.coinTouched = true;
                if (tile1 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxLeftCol] = 0;
                } else if (tile2 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxLeftCol] = 0;
                }
                
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
            if (tile1 == 4 || tile2 == 4) {
                player.coinTouched = true;
                if (tile1 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxTopRow][hitBoxRightCol] = 0;
                } else if (tile2 == 4) {
                    maze.mazeCreate.mazeGrid[hitBoxBottomRow][hitBoxRightCol] = 0;
                }
                
            }
            if (tile1 == 8 || tile2 == 8) {
                player.reachedEnd = true;
            }

        }

    }
    
}
