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
        pic = new PicPanel("F:/timg.jpg"); //���޸�
        setLayout(null);
        add(pic);
        picX = (int)((Width - pic.getWidth())/2);
        picY = (int)((Height - pic.getHeight())/2);
        
        pic.setLocation(picX, picY);  //��λ
        
        //��궯�� ������ ע��
        addMouseListener(
            new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    //��� ��� �Ƿ���ͼƬ��,ֻ�������ͼƬ��ʱ ��������
                    if(inPicBounds(e.getX(), e.getY()))
                    {
                        frontX = e.getX();
                        frontY = e.getY();
                        inThePic = true;
                    }
                    //��¼��ǰ����
                }
                //�ͷ�
                public void mouseReleased(MouseEvent e)
                {
                    inThePic = false;
                }
            }
        );
        
        //����ƶ� ������ ע��
        addMouseMotionListener(
            new MouseMotionAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {
                    if(inThePic && checkPoint(e.getX(),e.getY()))
                    {
                        //�߽� ���
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
   
    //��� ��(x,y) �Ƿ���ͼƬ��
    private boolean inPicBounds(int px,int py){
        if(px >= picX && px <= picX + pic.getWidth() &&
                            py >= picY && py <= picY + pic.getHeight())
            return true;
        else
            return false;
    }
    
    //Խ�� ���
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
        JFrame jframe = new JFrame("ͼƬ�϶���ʵ��");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.getContentPane().add(jpanel);
        jframe.setSize(Width, Height);
        jframe.setVisible(true);
    }
}
//��������ͼƬ
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
