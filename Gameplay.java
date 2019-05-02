import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener
{
	//snakelength in x axis and y axis
	private int[] snakexlength=new int[750];
	private int[] snakeylength=new int[750];
	
	//these are keystrokes
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	//adding image of its faces
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;
	private ImageIcon enemyimage;
	
	//timer to manage speed
	private Timer timer;
	private int delay=100;
	
	//adding body image
	private ImageIcon snakeimage;
	
	private int lengthofsnake=3;
	private int move=0;
	private ImageIcon tittleImage;
	
	//food random space
	private int [] enemyxpos= {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int [] enemyypos= {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private Random random=new Random();
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(23);
	
	//score
	private int score=0;
	//some changes needed
	private int notify=0;
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
		
	}
	public void paint(Graphics g)
	{
		if(move==0)
		{
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
			
			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
		}
		//tittle
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		//tittle image
		tittleImage =new ImageIcon("snaketitle.jpg");
		tittleImage.paintIcon(this, g, 25, 11);
		
		//gameplay area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 576);
		
		//gameplay background
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		//score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,16));
		g.drawString("Score : "+score, 780, 30);
		//length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,16));
		g.drawString("length : "+lengthofsnake, 780, 50);
		
		
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a=0;a<lengthofsnake;a++)
		{
			if(a==0 && right)
			{
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && left)
			{
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && down)
			{
				downmouth=new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && up)
			{
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a!=0)
			{
				snakeimage=new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		enemyimage =new ImageIcon("enemy.png");
		
		if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0])
		{
			score=score+10;
			lengthofsnake++;
			xpos=random.nextInt(34);
			ypos=random.nextInt(23);
		}
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		//body collision
		for(int b=1;b<lengthofsnake;b++)
		{
			if(snakexlength[b]==snakexlength[0]&& snakeylength[b]==snakeylength[0])
			{
				up=false;
				down=false;
				right=false;
				left=false;
				g.setColor(Color.RED);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Press Space to Restart", 330, 340);
				notify=1;
			}
		}
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(right)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]+25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]>850)
				{
					snakexlength[r]=25;
				}
				repaint();
			}
		}
		if(left)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]-25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]<25)
				{
					snakexlength[r]=850;
				}
				repaint();
			}
		}
		if(up)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]-25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]<75)
				{
					snakeylength[r]=625;
				}
				repaint();
			}
		}
		if(down)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]+25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]>625)
				{
					snakeylength[r]=75;
				}
				repaint();
			}
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE && notify==1)
		{
			score=0;
			move=0;
			lengthofsnake=3;
			repaint();
			notify=0;
		}
		if((e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) && notify==0)
		{
			move++;
			right=true;
			if(!left)
			{
				right=true;
			}
			else
			{
				right=false;
				left=true;
			}
			up=false;
			down=false;
			
		}
		if((e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A)&& notify==0)
		{
			move++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
			}
			up=false;
			down=false;
			
		}
		if((e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W)&& notify==0)
		{
			move++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
			}
			left=false;
			right=false;
			
		}
		if((e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S)&& notify==0)
		{
			move++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				down=false;
				up=true;
			}
			left=false;
			right=false;
			
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
