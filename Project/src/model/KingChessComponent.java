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
public class KingChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image KING_WHITE;
    private static Image KING_BLACK;
    private char name;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image kingImage;


    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("images/king-white.png"));
        }

        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("images/king-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = KING_WHITE;
                name = 'k';
            } else if (color == ChessColor.BLACK) {
                kingImage = KING_BLACK;
                name = 'K';
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKingImage(color);
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
        int r1 = Math.max(source.getX(),destination.getX()),
                c1 = Math.max(source.getY(),destination.getY()),
                r2 = Math.min(source.getX(),destination.getX()),
                c2 = Math.min(source.getY(),destination.getY());
        if((Math.abs(r1-r2)+Math.abs(c1-c2)!=1)&& !(Math.abs(r1-r2)==1&&sameDiagonal(source,destination))){
            return canCastle(source,destination);
        }
        return true;
    }

    public boolean canCastle(ChessboardPoint a,ChessboardPoint b){
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
        g.drawImage(kingImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
