package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 这个类表示国际象棋里面的车
 */
public class PawnChessComponent extends ChessComponent {
    boolean promote = false;
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private char name;



    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image pawnImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("images/pawn-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                name = 'p';
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                name = 'P';
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char getChessName() {
        return this.name;
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }


    /**
     * 车棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 车棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) throws IOException {
        ChessboardPoint source = getChessboardPoint();
        if(pawnImage==PAWN_BLACK){
            if(source.getX()==1&&destination.getX()==3&&destination.getY()==source.getY()&&chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
                return true;
            }
            else{
                if(Math.abs(destination.getY()-source.getY())==1&&destination.getX()-source.getX()==1){
                    if(!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                        if(destination.getX()==7) {
                            promote =true;
                        }
                        return true;
                    }
                }else if(destination.getX()-source.getX()==1&&destination.getY()==source.getY()&&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                    if(destination.getX()==7) {
                        promote = true;
                    }
                    return true;
                }
            }
        }
        if(pawnImage==PAWN_WHITE){
            if(source.getX()==6&&destination.getX()==4&&destination.getY()==source.getY()&&chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent){
                return true;
            }
            else{
                if(Math.abs(destination.getY()-source.getY())==1&&source.getX()-destination.getX()==1){
                    if(!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                        if (destination.getX() == 7) {
                            promote = true;
                        }
                        return true;
                    }
                }else if(source.getX()-destination.getX()==1&&destination.getY()==source.getY()&&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                    if(destination.getX()==7) {
                        promote = true;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pawnSwapLegal(ChessboardPoint a,ChessboardPoint b){
        return false;
    }



    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    public boolean canPromote(){
        return promote;
    }

    public void setPromote(boolean promote){
        this.promote = promote;
    }
}