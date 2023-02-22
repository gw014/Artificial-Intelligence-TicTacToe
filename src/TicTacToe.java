import java.awt.event.*;

import javax.swing.JOptionPane;

public class TicTacToe implements ActionListener{

    GUI gui;
    AI ai;

    String[][] tiles;

    boolean playerTurn = true;
    boolean alphaBeta = false;

    public enum GameState 
    {
        game_in_progress, x_victory, o_victory, draw
    }

    TicTacToe()
    {
        gui = new GUI();
        ai = new AI();
        ai.game = this;
        tiles = new String[3][3];

        //Initiailze tiles to an empty space
        for(int row = 0; row < tiles.length; row++)
        {
            for(int col = 0; col < tiles[0].length; col++)
            {
                tiles[row][col] = " ";
            }
        }
        
        gui.addListeners(this);

        newGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(e.getSource() == gui.toolbarPanel.aiFirstMove)
        {
            playerTurn = false;
            newGame();
        }
        else if(e.getSource() == gui.toolbarPanel.playerFirstMove)
        {
            playerTurn = true;
            newGame();
        }

        if(e.getSource() == gui.toolbarPanel.withPruning)
        {
            alphaBeta = true;
            newGame();
        }
        else if(e.getSource() == gui.toolbarPanel.withoutPruning)
        {
            alphaBeta = false;
            newGame();
        }

        if(checkGameState() != GameState.game_in_progress)
        {
            return;
        }

        for(int row = 0; row < gui.tilePanel.tiles.length; row++)
        {
            for(int col = 0; col < gui.tilePanel.tiles[0].length; col++)
            {
                if(e.getSource() == gui.tilePanel.tiles[row][col] && tiles[row][col].equals(" "))
                {
                    move(row, col, true);

                    if(checkGameState() == GameState.game_in_progress)
                    {
                        int[] aiMove;

                        if(alphaBeta)
                        {
                            aiMove = ai.chooseMoveAlphaBeta();
                        }
                        else
                        {
                            aiMove = ai.chooseMove();
                        }
                        
                        move(aiMove[0], aiMove[1], false);
                    }
                    
                }
            }
        }
    }

    void newGame()
    {
        //Initialize tiles to an empty space
        for(int row = 0; row < tiles.length; row++)
        {
            for(int col = 0; col < tiles[0].length; col++)
            {
                tiles[row][col] = " ";
                gui.tilePanel.clearTile(row, col);
            }
        }

        if(gui.toolbarPanel.aiFirstMove.isSelected())
        {
            playerTurn = false;
        }
        else if(gui.toolbarPanel.playerFirstMove.isSelected())
        {
            playerTurn = true;
        }

        if(gui.toolbarPanel.withPruning.isSelected())
        {
            alphaBeta = true;
        }
        else if(gui.toolbarPanel.withoutPruning.isSelected())
        {
            alphaBeta = false;
        }
        
        if(playerTurn == false)
        {
            int[] aiMove;

            if(alphaBeta)
            {
                aiMove = ai.chooseMoveAlphaBeta();
            }
            else
            {
                aiMove = ai.chooseMove();
            }

            move(aiMove[0], aiMove[1], playerTurn);
        }

    }

    void move(int x, int y, boolean player)
    {
        if(player)
        {
            tiles[x][y] = "O";
        }
        else
        {
            tiles[x][y] = "X";
        }
        
        gui.tilePanel.markTile(x, y, player);

        if(checkGameState() == GameState.draw)
        {
            int answer = gui.showResults("Draw.");

            if (answer  == JOptionPane.YES_OPTION) 
            {
                // Yes button was pressed
                newGame();
                newGame();
                
            } 
            else if (answer  == JOptionPane.NO_OPTION) 
            {
                System.exit(0);
            }
        }
        else if(checkGameState() == GameState.x_victory)
        {
            int answer = gui.showResults("X Wins!");

            if (answer  == JOptionPane.YES_OPTION) 
            {
                // Yes button was pressed
                newGame();
            } 
            else if (answer  == JOptionPane.NO_OPTION) 
            {
                System.exit(0);
            }
        }
        else if(checkGameState() == GameState.o_victory)
        {
            int answer = gui.showResults("O Wins!");

            if (answer  == JOptionPane.YES_OPTION) 
            {
                // Yes button was pressed
                newGame();
                
            } 
            else if (answer  == JOptionPane.NO_OPTION) 
            {
                System.exit(0);
            }
        }
        else
        {
            playerTurn = !playerTurn;
        }
        
    }

    
    GameState checkGameState()
    {   
        //Checks crosses, rows, and columns for 3 in a row
        for(int i = 0; i < 8; i++)
        {
            switch(i) {
                case 0:
                    if(tiles[0][0].equals(tiles[1][1]) && tiles[1][1].equals(tiles[2][2]) && !tiles[0][0].equals(" "))
                    {
                        if(tiles[0][0].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 1:
                    if(tiles[2][0].equals(tiles[1][1]) && tiles[1][1].equals(tiles[0][2]) && !tiles[2][0].equals(" "))
                    {
                        if(tiles[2][0].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 2:
                    if(tiles[0][0].equals(tiles[0][1]) && tiles[0][1].equals(tiles[0][2]) && !tiles[0][0].equals(" "))
                    {
                        if(tiles[0][0].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 3:
                    if(tiles[1][0].equals(tiles[1][1]) && tiles[1][1].equals(tiles[1][2]) && !tiles[1][0].equals(" "))
                    {
                        if(tiles[1][0].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 4:
                    if(tiles[2][0].equals(tiles[2][1]) && tiles[2][1].equals(tiles[2][2]) && !tiles[2][0].equals(" "))
                    {
                        if(tiles[2][0].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 5:
                    if(tiles[0][0].equals(tiles[1][0]) && tiles[1][0].equals(tiles[2][0]) && !tiles[0][0].equals(" "))
                    {
                        if(tiles[0][0].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 6:
                    if(tiles[0][1].equals(tiles[1][1]) && tiles[1][1].equals(tiles[2][1]) && !tiles[0][1].equals(" "))
                    {
                        if(tiles[0][1].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
                case 7:
                    if(tiles[0][2].equals(tiles[1][2]) && tiles[1][2].equals(tiles[2][2]) && !tiles[0][2].equals(" "))
                    {
                        if(tiles[0][2].equals("X"))
                        {
                            return GameState.x_victory;
                        }
                        else
                        {
                            return GameState.o_victory;
                        }
                    }
                    break;
            }
        }

        if(isDraw())
        {
            return GameState.draw;
        }
        else
        {
            return GameState.game_in_progress;
        }
    }

    boolean isDraw()
    {
        for(int row = 0; row < tiles.length; row++)
        {
            for(int col = 0; col < tiles[0].length; col++)
            {
                if(tiles[row][col].equals(" "))
                {
                    return false;
                }
            }
        }

        return true;
    }

}
