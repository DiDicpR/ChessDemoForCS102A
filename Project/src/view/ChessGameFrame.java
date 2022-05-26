package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    protected int WIDTH;
    protected int HEIGHT;
    public int CHESSBOARD_SIZE;
    private GameController gameController;

    protected boolean pawnPromote = false;

    public ChessGameFrame(int width, int height) {
        PaintChessGameFrame(width,height);

    }

    public boolean setPawnPromote(boolean pawnPromote){
        return this.pawnPromote = pawnPromote;
    }
    public void PaintChessGameFrame(int width, int height){
        setTitle("2022 CS102A Project"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        if(pawnPromote==true) {
            addPawnPromote();
        }

        addChessboard();
        addHelloButton();
        addLoadButton();
        addRetractButton();
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard);
    }

    /**
     * 在游戏面板中添加标签
     */
    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGHT, HEIGHT / 10 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGHT, HEIGHT / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    private void addRetractButton() {
        JButton button = new JButton("Retract");
        button.setLocation(HEIGHT, HEIGHT / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addPawnPromote(){
        JButton button1 = new JButton("Queen");
        button1.setLocation(HEIGHT, HEIGHT/ 10 );
        button1.setSize(200, 60);
        button1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button1);
        JButton button2 = new JButton("Rook");
        button2.setLocation(HEIGHT, HEIGHT / 10 + 120);
        button2.setSize(200, 60);
        button2.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button2);
        JButton button3 = new JButton("Knight");
        button3.setLocation(HEIGHT, HEIGHT / 10 + 240);
        button3.setSize(200, 60);
        button3.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button3);
        JButton button4 = new JButton("Bishop");
        button4.setLocation(HEIGHT, HEIGHT / 10 + 360);
        button4.setSize(200, 60);
        button4.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button4);
        pawnPromote = false;
    }
}
