package ui;

import view.ChessGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI {
    private JFrame mainFrame;   //主窗口

    public void mainFrame() {

        mainFrame = new JFrame("Chess");    //主窗口
        mainFrame.setSize(732, 850);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void menuUI() {

        mainFrame.setLayout(null);

        JLabel title = new JLabel(new ImageIcon("res1.png"));
        title.setVisible(true);
        title.setBounds(166, 100, 400, 300);
        mainFrame.add(title);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(266, 300, 200, 700);
        menuPanel.setVisible(true);
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));
        Font buttonF = new Font("Rockwell" +
                "", Font.BOLD, 20);
        JButton start = new JButton("Nick Nack");
        JButton pve = new JButton("Play With Computer");
        JButton loadLast = new JButton("Load Last Game");
        JButton quit = new JButton("Exit");
        start.setFont(buttonF);
        pve.setFont(buttonF);
        loadLast.setFont(buttonF);
        quit.setFont(buttonF);
        start.setBackground(Color.pink);
        pve.setBackground(Color.cyan);
        loadLast.setBackground(Color.yellow);
        start.setPreferredSize(new Dimension(400, 48));
        pve.setPreferredSize(new Dimension(400, 48));
        loadLast.setPreferredSize(new Dimension(400, 48));
        quit.setPreferredSize(new Dimension(400, 48));
        start.addMouseListener(new MouseUIReaction(1));
        pve.addMouseListener(new MouseUIReaction(2));
        quit.addMouseListener(new MouseUIReaction(3));
        loadLast.addMouseListener(new MouseUIReaction(4));
        menuPanel.add(start);
        menuPanel.add(pve);
        menuPanel.add(loadLast);
        menuPanel.add(quit);
        mainFrame.add(menuPanel);

        JLabel backGround = new JLabel(new ImageIcon("images/Background1.jpg"));
        backGround.setVisible(true);
        backGround.setBounds(-100, -20, 900, 900);
        mainFrame.add(backGround);

        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    public void gameFrame() {

        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        });
    }



    /**
     * 鼠标UI交互类
     */
    private class MouseUIReaction extends MouseAdapter {

        private final int type;

        @Override
        public void mousePressed(MouseEvent e) {
            if (type == 1) {
                gameFrame();
            }
        }

        /**
         * 鼠标UI互动二类构造函数
         *
         * @param type 互动类型
         */
        public MouseUIReaction(int type) {

            this.type = type;
        }
    }

    public static void main(String[] args) {

        MainUI game = new MainUI();
        game.mainFrame();
        game.menuUI();
    }
}