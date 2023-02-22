import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUI extends JFrame {

    JPanel mainPanel;
    TilePanel tilePanel;
    BannerPanel bannerPanel;
    ToolbarPanel toolbarPanel;

    JPanel splitPanel;

    final Color backgroundColor = new Color(202, 183, 132);

    GUI()
    {
        mainPanel = new JPanel(new BorderLayout());

        splitPanel = new JPanel();
        splitPanel.setLayout(new BorderLayout());

        tilePanel = new TilePanel();
        bannerPanel = new BannerPanel();
        toolbarPanel = new ToolbarPanel();
        
        splitPanel.add(bannerPanel, BorderLayout.PAGE_START);
        splitPanel.add(tilePanel, BorderLayout.CENTER);
        splitPanel.add(toolbarPanel, BorderLayout.PAGE_END);

        mainPanel.add(splitPanel);

        setSize(600, 600);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
    }

    public int showResults(String results)
    {
        //JOptionPane.showMessageDialog(this, results);

        int answer = JOptionPane.showConfirmDialog(this, results + " Play again?", "Game Over", JOptionPane.YES_NO_OPTION);

        return answer;
    }

    public void addListeners(ActionListener actionListener)
    {
        for(int i = 0; i < tilePanel.tiles.length; i++)
        {
            for(int j = 0; j < tilePanel.tiles[0].length; j++)
            {
                tilePanel.tiles[i][j].addActionListener(actionListener);
            }
        }

        toolbarPanel.playerFirstMove.addActionListener(actionListener);
        toolbarPanel.aiFirstMove.addActionListener(actionListener);
        toolbarPanel.withPruning.addActionListener(actionListener);
        toolbarPanel.withoutPruning.addActionListener(actionListener);
    }

    class TilePanel extends JPanel {

        JButton[][] tiles = new JButton[3][3];

        TilePanel()
        {
            setLayout(new GridLayout(tiles.length, tiles[0].length));
            addTiles();
        }

        void addTiles()
        {

            for(int i = 0; i < tiles.length; i++)
            {
                for(int j = 0; j < tiles[0].length; j++)
                {
                    tiles[i][j] = new JButton(" ");
                    tiles[i][j].setFocusPainted(false);
                    tiles[i][j].setForeground(Color.BLACK);
                    tiles[i][j].setBackground(backgroundColor);
                    tiles[i][j].setFont(new Font("Times New Roman", Font.BOLD, 50));
                    add(tiles[i][j]);
                }
            }
        }

        void markTile(int tileX, int tileY, boolean playerTurn)
        {
            if(playerTurn)
            {
                tiles[tileX][tileY].setText("O");
            }
            else
            {
                tiles[tileX][tileY].setText("X");
            }
        }

        void clearTile(int tileX, int tileY)
        {
            tiles[tileX][tileY].setText(" ");
        }
    }

    class BannerPanel extends JPanel {

        JLabel title;

        BannerPanel()
        {
            setBackground(backgroundColor);
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.black, 2));
            addTitle();
        }

        void addTitle()
        {
            title = new JLabel("Tic-Tac-Toe");
            title.setFont(new Font("Times New Roman", Font.BOLD, 25));
            add(title);
        }
    }

    class ToolbarPanel extends JPanel 
    {

        JLabel firstMoveLabel;
        JLabel algorithmnLabel;
        
        JRadioButton aiFirstMove;
        JRadioButton playerFirstMove;

        JRadioButton withPruning;
        JRadioButton withoutPruning;

        ToolbarPanel()
        {
            setBackground(backgroundColor);
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(Color.black, 2));
            addRadioButtons();
        }

        void addRadioButtons()
        {
            firstMoveLabel = new JLabel("First Move: ");
            ButtonGroup moveButtonGroup = new ButtonGroup();
            aiFirstMove = new JRadioButton("AI (X)", true);
            aiFirstMove.setBackground(backgroundColor);
            playerFirstMove = new JRadioButton("You (O)");
            playerFirstMove.setBackground(backgroundColor);
            moveButtonGroup.add(aiFirstMove);
            moveButtonGroup.add(playerFirstMove);

            algorithmnLabel = new JLabel("Minimax: ");
            ButtonGroup algorithmButtonGroup = new ButtonGroup();
            withoutPruning = new JRadioButton("Without Pruning", true);
            withoutPruning.setBackground(backgroundColor);
            withPruning = new JRadioButton("With Pruning");
            withPruning.setBackground(backgroundColor);
            algorithmButtonGroup.add(withoutPruning);
            algorithmButtonGroup.add(withPruning);

            add(firstMoveLabel);
            add(aiFirstMove);
            add(playerFirstMove);
            add(algorithmnLabel);
            add(withoutPruning);
            add(withPruning);
        }
    }
}