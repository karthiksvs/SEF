package baselineLongCalculator;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class SquareRoot implements ActionListener,KeyListener
{
    Frame f;
    Label one,two;
    TextField three,four;
    Button five;
    Double i,t;
    String s1="";
    public SquareRoot()
    {
        f=new Frame("square root");
        one=new Label("Enter the number");
        two=new Label("square root is ");
        three=new TextField(5);
        four=new TextField(5);
        five=new Button("Click here for result");
        f.setSize(400,400);
        f.setVisible(true);
        f.add(one);
        f.add(two);
        f.add(three);
        f.add(four);
        f.add(five);
        f.setLayout(null);
        one.setBounds(20,20,140,40);
        two.setBounds(20,250,140,40);
        three.setBounds(180,30,140,40);
        four.setBounds(180,250,140,40);
        five.setBounds(100,150,240,40);
        three.addKeyListener(this);
        five.addActionListener(this);
    }
    public void keyPressed(KeyEvent k)
    {
        System.out.print("");
    }
    public void keyTyped(KeyEvent k)
    {
        s1+= k.getKeyChar();
    }
    public void keyReleased(KeyEvent k)
    {
        System.out.print("");
    }
    public void actionPerformed(ActionEvent ae)
    {
        
        try
        {
            t=Double.parseDouble(s1); 
        }
        catch(NumberFormatException e)
        {
            System.out.print("please type  the correct number");
        }
        for(i=1.0;i<=t;)
        {
            //System.out.println((double)(i*i));
            
            if(((float)(i*i)/t==.99)||(float)(i*i)/t==1.0)
            {
                four.setText(Math.ceil(i)+"");
                System.out.print(Math.ceil(i));
                break;
                 
            }
            
            i=i+.01;
        }
        System.exit(1);
    }
}