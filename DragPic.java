package pkg1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragPic extends JPanel{
    static int Width = 1000;
    static int Height = 600;
        
    PicPanel pic = null;
    private int picX;
    private int picY;
    int frontX = 0;
    int frontY = 0;
    
    boolean inThePic = false;
    
    public DragPic(){
        pic = new PicPanel("F:/timg.jpg"); //可修改
        setLayout(null);
        add(pic);
        picX = (int)((Width - pic.getWidth())/2);
        picY = (int)((Height - pic.getHeight())/2);
        
        pic.setLocation(picX, picY);  //定位
        
        //鼠标动作 监听器 注册
        addMouseListener(
            new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    //检测 落点 是否在图片上,只有落点在图片上时 才起作用
                    if(inPicBounds(e.getX(), e.getY()))
                    {
                        frontX = e.getX();
                        frontY = e.getY();
                        inThePic = true;
                    }
                    //记录当前坐标
                }
                //释放
                public void mouseReleased(MouseEvent e)
                {
                    inThePic = false;
                }
            }
        );
        
        //鼠标移动 监听器 注册
        addMouseMotionListener(
            new MouseMotionAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {
                    if(inThePic && checkPoint(e.getX(),e.getY()))
                    {
                        //边界 检查
                        picX =picX - (frontX - e.getX());
                        picY =picY - (frontY - e.getY());
                        //System.out.println("pic_x=" + pic_x);
                        frontX = e.getX();
                        frontY = e.getY();
                        pic.setLocation(picX, picY);
                    }
                }
            }
        );
    }
   
    //检测 点(x,y) 是否在图片上
    private boolean inPicBounds(int px,int py){
        if(px >= picX && px <= picX + pic.getWidth() &&
                            py >= picY && py <= picY + pic.getHeight())
            return true;
        else
            return false;
    }
    
    //越界 检查
    private boolean checkPoint(int px, int py){
        if(px <0 || py <0)
            return false;
        if(px >getWidth() || py > getHeight())
            return false;
        return true;
    }
        
    public static void main(String[] args)
    {
        JPanel jpanel = new DragPic();
        JFrame jframe = new JFrame("图片拖动的实现");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.getContentPane().add(jpanel);
        jframe.setSize(Width, Height);
        jframe.setVisible(true);
    }
}
//用来放置图片
class PicPanel extends JPanel
{
    int pWidth = 0;
    int pHeight = 0;
    Image im = null;
    
    int i = 0; 
    public PicPanel(String picName){
        ImageIcon imageIcon = new ImageIcon(picName);
        im = imageIcon.getImage();
        pWidth = imageIcon.getIconWidth();
        pHeight = imageIcon.getIconHeight();
        setBounds(0,0,pWidth, pHeight);
    }
    
    public void paint(Graphics g){
        g.drawImage(im,0,0,pWidth,pHeight,null);
    }
}
