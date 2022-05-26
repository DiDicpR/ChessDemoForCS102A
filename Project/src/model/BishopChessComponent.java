package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的车
 */
public class BishopChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private char name;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image bishopImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("images/bishop-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
                name = 'b';
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
                name = 'B';
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
    }

    public boolean sameDiagonal(ChessboardPoint a,ChessboardPoint b){
        if(Math.abs(a.getX()-b.getX())==Math.abs(a.getY()-b.getY()))
            return true;
        else
            return false;
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (!sameDiagonal(source,destination)) {
            return false;
        }
        if(source.getX()<destination.getX()&&source.getY()<destination.getY()){
            for(int i=source.getX();i<destination.getX();i++) {
                for(int j=source.getY();j<destination.getY();j++) {
                    if(i!=source.getX()&&j!=source.getY()) {
                        ChessboardPoint a = new ChessboardPoint(i, j);
                        if (sameDiagonal(a, source)) {
                            if (!(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        if(source.getX()>destination.getX()&&source.getY()>destination.getY()){
            for(int i=destination.getX()+1;i<=source.getX();i++) {
                for(int j=destination.getY()+1;j<=source.getY();j++) {
                    if(i!=source.getX()&&j!=source.getY()) {
                        ChessboardPoint a = new ChessboardPoint(i, j);
                        if (sameDiagonal(a, source)) {
                            if (!(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        if(source.getX()<destination.getX()&&source.getY()>destination.getY()){
            for(int i=source.getX();i<destination.getX();i++) {
                for(int j=destination.getY()+1;j<=source.getY();j++) {
                    if(i!=source.getX()&&j!=source.getY()) {
                        ChessboardPoint a = new ChessboardPoint(i, j);
                        if (sameDiagonal(a, source)) {
                            if (!(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        if(source.getX()>destination.getX()&&source.getY()<destination.getY()){
            for(int i=destination.getX()+1;i<=source.getX();i++) {
                for(int j=source.getY();j<destination.getY();j++) {
                    if(i!=source.getX()&&j!=source.getY()) {
                        ChessboardPoint a = new ChessboardPoint(i, j);
                        if (sameDiagonal(a, source)) {
                            if (!(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
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
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}

