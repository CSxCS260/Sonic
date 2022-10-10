import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

class SonicGameplay extends JComponent{
	double x = 0,y = 0,velx = 0,vely = 0;
	private static final long serialVersionUID = 1L;
	private Image backgroundImg;
	private Image sonicImg;
	private Image enemyImg1;
	private Image coinImage;
	private int jumpAmount;
	private int imageX;
	private int imageY;
	private int backgroundX;
	private int backgroundY;
	private int eImageY1;
	private int eImageY2;
	private int eImageY3;
	private int eImageY4;
	private int eImageY5;
	private int eImageY6;
	private int eImageY7;
	private int eImageY8;
	private int translationX;
	private int translationY;
	private boolean endGame;
	private boolean winGame;
	private boolean spinning;
	private boolean leaping;
	private int coins;
	private boolean hitE1;
	private boolean hitE2;
	private boolean hitE3;
	private boolean coin1;
	private boolean coin2;
	private boolean coin3;
	private boolean coin4;
	private boolean coin5;
	public SonicGameplay() {
		backgroundImg = new ImageIcon("SonicBackground2.png").getImage();
		sonicImg = new ImageIcon("SonicStandingStill.gif").getImage();
		enemyImg1 = new ImageIcon("MotobugMoving3.gif").getImage();
		coinImage = new ImageIcon("SonicRing.png").getImage();
		jumpAmount = 80;
		backgroundX = 0;
		backgroundY = 0;
		imageX = 0;
		imageY = 1170;
		translationX = 0; //Do not change here, this is how background moves
		translationY = -750; // Do not change here, this is how background moves
		endGame = false;
		winGame = false;
		spinning = false;
		leaping = false;
		hitE1 = false;
		hitE2 = false;
		hitE3 = false;
		coin1 = false;
		coin2 = false;
		coin3 = false;
		coin4 = false;
		coin5 = false;
		coins = 0;
		
	}
	public int getCoins() {
		return coins;
	}
	public int getUpdatedScore() {
		int enemyScore = 0;
		if (hitE1) {
			enemyScore+=20;
		}
		if (hitE2) {
			enemyScore+=20;
		}
		if (hitE3) {
			enemyScore+=20;
		}
		if(endGame) {
			return (coins*10)+enemyScore;
		}
		else {
			return enemyScore;
		}
	}
	public void die() {
		if (getCoins()>0) {
			imageX = 0;
			imageY= 1170;
			coins=0;
			translationX+=1000;
			Timer loseCoins;
			class playLosingCoins implements ActionListener{
				int i = -100;
				public void actionPerformed(ActionEvent e) {
					if (i < 100) {
						i++;
						sonicImg = new ImageIcon("SonicLosingRings4.gif").getImage();
						imageY+=i/15;
						}
					}
				}
				loseCoins = new Timer(1, new playLosingCoins());
				loseCoins.start();
			}
		else {
			imageX = 0;
			Timer dying;
			class playDying implements ActionListener{
				int i = -40;
				public void actionPerformed(ActionEvent e) {
					if (i < 0) {
						i++;
						imageY-=1;
						sonicImg = new ImageIcon("SonicLeaningBackward4.gif").getImage();
					}
					else if(i<50) {
						i++;
						imageY+=4;
						sonicImg = new ImageIcon("SonicLeaningBackward4.gif").getImage();
					}
					else if(i==50) {
						endGame = true;
						winGame = false;						
					}
				}
			}
			dying = new Timer(1, new playDying());
			dying.start();
		}
	}
	public void jogSonicXF() {
		if(translationX>=-13800) {
			sonicImg = new ImageIcon("SonicRunningMedium2.gif").getImage();
			translationX-=20;
			leaping=false;
			spinning = false;
		}
		imageX = 45;
		imageY = 1180;
	}
	public void jogSonicXB() {
		if(translationX<=0&&translationX>=-13800) {
			sonicImg = new ImageIcon("SonicRunningMediumB.gif").getImage();
			translationX+=20;
			leaping=false;
			spinning = false;
		}
		imageX = 0;
		imageY = 1180;
	}
	public void spinSonicXF() {
		if(translationX>=-13800) {
			sonicImg = new ImageIcon("SonicSpinning3.gif").getImage();
			translationX-=25;
			spinning = true;
			leaping=false;
		}
		imageX = 10;
		imageY = 1200;
	} 
	public void spinSonicXB() {
		if(translationX<=0&&translationX>=-13800) {
			sonicImg = new ImageIcon("SonicSpinningB.gif").getImage();
			translationX+=25;
			spinning = true;
			leaping=false;

		}
		imageX = 10;
		imageY = 1200;	
	}
	public void jumpSonic2() {
		if(jumpAmount==-90) {
			jumpAmount=80;
		}
		sonicImg = new ImageIcon("SonicSpinning3.png").getImage();
		imageY-=jumpAmount-5;
		jumpAmount-=10;
	}
	public void jumpSonic() {
		sonicImg = new ImageIcon("SonicJumpingUp3.gif").getImage();
		imageX = -200;
		imageY = 730;
		leaping=true;
		spinning = true;
	}
	public void crouchSonic() {
		sonicImg = new ImageIcon("SonicCrouching5.png").getImage();
		imageX = 83;
		imageY = 1240;
		leaping=false;
		spinning = false;
	}
	public void standStillSonic() {
		sonicImg = new ImageIcon("SonicStandingStill.gif").getImage();
		imageX = 0;
		imageY = 1170;
		leaping=false;
		spinning = false;
	}
	public void leapSonicF() {
		if(translationX>=-13800) {
			sonicImg = new ImageIcon("SonicJumpingUp3.gif").getImage();
			imageX = -200;
			imageY = 730;
			translationX-=35;
			leaping = true;
			spinning = true;
		}	
	}
	public void leapSonicB() {
		if(translationX<=0&&translationX>=-13800) {
			sonicImg = new ImageIcon("SonicJumpingUp3.gif").getImage();
			imageX = -200;
			imageY = 730;
			translationX+=35;
			leaping = true;
			spinning = true;
		}
	}
	public void obstacle() {
		if(translationX<-1000&&translationX>-1400&&hitE3==false) {
			if(spinning==true) {
				eImageY3=-500000;
				hitE3 = true;
			}
			else if(spinning==false) {
				die();
			}
		}
		if (translationX<=-7355&&translationX>-7830&&!leaping) {
			translationX=-7350;
		}
		if(translationX<=-8860&&translationX>-9010&&!leaping){
			translationX=-8000;
		}
		if(translationX<=-10700&&translationX>-11500&&!leaping) {
			die();
		}
		if(translationX<-5000&&translationX>-5400&&!hitE1) {
			if(spinning==true) {
				eImageY1=-500000;
				hitE1 = true;
			}
			else if(spinning==false) {
				die();
			}
		}
		if (translationX<=-12800&&translationX>-13315&&!leaping) {
			translationX=-12795;
		}
		if(translationX<-10000&&translationX>-10400&&!hitE2) {
			if(spinning==true) {
				eImageY2=-500000;
				hitE2 = true;
			}
			else if(spinning==false) {
				die();
			}
		}
		if(translationX<-600&&translationX>-630&&!coin1) {
			eImageY4=-500000;
			coin1=true;
			coins++;
		}
		if(translationX<-400&&translationX>-430&&!coin2) {
			eImageY5=-500000;
			coin2=true;
			coins++;
		}
		if(translationX<-2000&&translationX>-2030&&!coin3) {
			eImageY6=-500000;
			coin3=true;
			coins++;
		}
		if (translationX<-12450&&translationX>-12480&&!coin4) {
			eImageY7=-500000;
			coin4=true;
			coins++;
		}
		if(translationX<-4600&&translationX>-4630&&!coin5) {
			eImageY8=-500000;
			coin5=true;
			coins++;
		}
	}
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		Graphics2D g2 = (Graphics2D) gr;
		Graphics2D g3 = (Graphics2D) gr;
		Graphics2D g4 = (Graphics2D) gr;
		Graphics2D g5 = (Graphics2D) gr;
		Graphics2D g6 = (Graphics2D) gr;
		Graphics2D g7 = (Graphics2D) gr;
		Graphics2D g8 = (Graphics2D) gr;
		Graphics2D g9 = (Graphics2D) gr;
		Graphics2D g10 = (Graphics2D) gr;
		setDoubleBuffered(true);
		AffineTransform t = new AffineTransform();
		t.translate(translationX,translationY);
		AffineTransform t2 = new AffineTransform();
		t2.translate(0,translationY);
		AffineTransform t3 = new AffineTransform();
		t3.translate(translationX+5150,translationY+1050);
		AffineTransform t4 = new AffineTransform();
		t4.translate(translationX+10150, translationY+1050);
		AffineTransform t5 = new AffineTransform();
		t5.translate(translationX+1150, translationY+1050);
		AffineTransform t6 = new AffineTransform();
		t6.translate(translationX+650, translationY+1300);
		AffineTransform t7 = new AffineTransform();
		t7.translate(translationX+450, translationY+1300);
		AffineTransform t8 = new AffineTransform();
		t8.translate(translationX+2050, translationY+1300);
		AffineTransform t9 = new AffineTransform();
		t9.translate(translationX+12450, translationY+1300);
		AffineTransform t10 = new AffineTransform();
		t10.translate(translationX+4600, translationY+1300);
		if (translationX<-2500&&translationX>=-3500) {
			imageX=-550;
			imageY = 1120;
			t2.rotate(Math.toRadians(-25));
			int translationXDigits = translationX/(-100);
			t.translate(0,(translationXDigits-25)*75);
		}
		
		//The peak of hill begins
		else if (translationX<-3500 && translationX>=-5800) {
			t.translate(0, 750);
		}
		
		//The peak of hill ends
		else if (translationX<-5800 && translationX>=-6800) {
			imageX= 550;
			imageY = 1020;
			t2.rotate(Math.toRadians(25));
			int translationXDigits=translationX/(100);
			t.translate(0, (translationXDigits+68)*75);
		}
		else if(translationX<=-9010&&translationX>-10700) {
			t.translate(0,160);
		}
		else if(translationX<-13800) {
			Timer trulyEnd;
			class letItPlayOnce implements ActionListener {
				boolean once = true;
				public void actionPerformed(ActionEvent e) {
					if (once) {
						once= false;
						endGame = true;
					}
				}
			}
			trulyEnd = new Timer(5000, new letItPlayOnce());
			trulyEnd.start();
			winGame = true;
			sonicImg = new ImageIcon("SonicGoodEnding6.gif").getImage();
			imageX = 0;
			imageY = 700;
			translationX = -13801;
			translationY = -750;
		}
		g.setTransform(t);
		g.drawImage(backgroundImg, backgroundX, backgroundY, this);
		g3.setTransform(t3);
		g3.drawImage(enemyImg1, 0, eImageY1,this);
		g4.setTransform(t4);
		g4.drawImage(enemyImg1, 0, eImageY2,this);
		g5.setTransform(t5);
		g5.drawImage(enemyImg1, 0, eImageY3,this);
		g6.setTransform(t6);
		g6.drawImage(coinImage, 0, eImageY4,this);
		g7.setTransform(t7);
		g7.drawImage(coinImage, 0, eImageY5,this);
		g8.setTransform(t8);
		g8.drawImage(coinImage, 0, eImageY6,this);
		g9.setTransform(t9);
		g9.drawImage(coinImage, 0, eImageY7,this);
		g10.setTransform(t10);
		g10.drawImage(coinImage, 0, eImageY8,this);
		g2.setTransform(t2);
		g2.drawImage(sonicImg, imageX, imageY, this);
	}
	public boolean closeGame() {
		return endGame;
	}
	public boolean winGame() {
		return winGame;
	}
	
	
}
