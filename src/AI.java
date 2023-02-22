public class AI {

    final static int DEPTH_LIMIT = 9;

    TicTacToe game;

    int checkBoard()
    {
        for(int i = 0; i < 8; i++)
        {
            switch(i) {
                case 0:
                    if(game.tiles[0][0].equals(game.tiles[1][1]) && game.tiles[1][1].equals(game.tiles[2][2]))
                    {
                        if(game.tiles[0][0].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[0][0].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 1:
                    if(game.tiles[2][0].equals(game.tiles[1][1]) && game.tiles[1][1].equals(game.tiles[0][2]))
                    {
                        if(game.tiles[2][0].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[2][0].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 2:
                    if(game.tiles[0][0].equals(game.tiles[0][1]) && game.tiles[0][1].equals(game.tiles[0][2]))
                    {
                        if(game.tiles[0][0].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[0][0].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 3:
                    if(game.tiles[1][0].equals(game.tiles[1][1]) && game.tiles[1][1].equals(game.tiles[1][2]))
                    {
                        if(game.tiles[1][0].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[1][0].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 4:
                    if(game.tiles[2][0].equals(game.tiles[2][1]) && game.tiles[2][1].equals(game.tiles[2][2]))
                    {
                        if(game.tiles[2][0].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[2][0].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 5:
                    if(game.tiles[0][0].equals(game.tiles[1][0]) && game.tiles[1][0].equals(game.tiles[2][0]))
                    {
                        if(game.tiles[0][0].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[0][0].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 6:
                    if(game.tiles[0][1].equals(game.tiles[1][1]) && game.tiles[1][1].equals(game.tiles[2][1]))
                    {
                        if(game.tiles[0][1].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[0][1].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
                case 7:
                    if(game.tiles[0][2].equals(game.tiles[1][2]) && game.tiles[1][2].equals(game.tiles[2][2]))
                    {
                        if(game.tiles[0][2].equals("X"))
                        {
                            return 1;
                        }
                        else if(game.tiles[0][2].equals("O"))
                        {
                            return -1;
                        }
                    }
                    break;
            }
        }

        return 0;
    }

    int[] chooseMove()
    {
        int[] move = new int[2];
        int bestMoveValue = Integer.MIN_VALUE;

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(game.tiles[row][col].equals(" "))
                {
                    game.tiles[row][col] = "X";
                    int moveValue = minimax(DEPTH_LIMIT, false);
                    game.tiles[row][col] = " ";

                    if(moveValue > bestMoveValue)
                    {
                        move[0] = row;
                        move[1] = col;
                        bestMoveValue = moveValue;
                    }
                }
            }
        }

        return move;
    }

    int minimax(int depth, boolean isMax)
    {
        int boardValue = checkBoard();

        if(Math.abs(boardValue) == 1 || depth == 0 || game.isDraw())
        {
            return boardValue;
        }

        if(isMax)
        {
            int best = Integer.MIN_VALUE;

            for(int row = 0; row < 3; row++)
            {
                for(int col = 0; col < 3; col++)
                {
                    if(game.tiles[row][col].equals(" "))
                    {
                        game.tiles[row][col] = "X";
                        best = Math.max(best, minimax(depth-1, false));
                        game.tiles[row][col] = " ";
                    }
                }
            }

            return best;
        }
        else
        {
            int best = Integer.MAX_VALUE;

            for(int row = 0; row < 3; row++)
            {
                for(int col = 0; col < 3; col++)
                {
                    if(game.tiles[row][col].equals(" "))
                    {
                        game.tiles[row][col] = "O";
                        best = Math.min(best, minimax(depth-1, true));
                        game.tiles[row][col] = " ";
                    }
                }
            }

            return best;
        }
    }

    int[] chooseMoveAlphaBeta()
    {
        int[] move = new int[2];
        int bestMoveValue = Integer.MIN_VALUE;

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(game.tiles[row][col].equals(" "))
                {
                    game.tiles[row][col] = "X";
                    int moveValue = minimaxAlphaBeta(DEPTH_LIMIT, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    game.tiles[row][col] = " ";

                    if(moveValue > bestMoveValue)
                    {
                        move[0] = row;
                        move[1] = col;
                        bestMoveValue = moveValue;
                    }
                }
            }
        }

        return move;
    }

    int minimaxAlphaBeta(int depth, boolean isMax, int alpha, int beta)
    {
        int boardValue = checkBoard();

        if(Math.abs(boardValue) == 1 || depth == 0 || game.isDraw())
        {
            return boardValue;
        }

        if(isMax)
        {
            int best = Integer.MIN_VALUE;

            for(int row = 0; row < 3; row++)
            {
                for(int col = 0; col < 3; col++)
                {
                    if(game.tiles[row][col].equals(" "))
                    {
                        game.tiles[row][col] = "X";
                        best = Math.max(best, minimaxAlphaBeta(depth-1, false, alpha, beta));
                        game.tiles[row][col] = " ";
                        alpha = Math.max(alpha, best);

                        if(alpha >= beta)
                        {
                            return best;
                        }
                    }
                }
            }

            return best;
        }
        else
        {
            int best = Integer.MAX_VALUE;

            for(int row = 0; row < 3; row++)
            {
                for(int col = 0; col < 3; col++)
                {
                    if(game.tiles[row][col].equals(" "))
                    {
                        game.tiles[row][col] = "O";
                        best = Math.min(best, minimaxAlphaBeta(depth-1, true, alpha, beta));
                        game.tiles[row][col] = " ";
                        beta = Math.min(beta, best);

                        if(beta <= alpha)
                        {
                            return best;
                        }
                    }
                }
            }

            return best;
        }
    }
}
