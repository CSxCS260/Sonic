import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
	public static void main(String[] args){
		runGame();
	}
	public static void runGame() {
		SonicGameplay playgame = new SonicGameplay();
		boolean shouldGameStart = true;
		while(shouldGameStart) {
			shouldGameStart = false;
			final int X_DIMENSION = 800;
			final int Y_DIMENSION = 600;
			
			
			
			//JFrame stuff
			JFrame firstFrame = new JFrame("Name Enter Screen");
			JFrame secondFrame = new JFrame("Sonic The Hedgehog");
			firstFrame.setPreferredSize(new Dimension(X_DIMENSION, Y_DIMENSION));
	//		firstFrame.setResizable(false);
			firstFrame.pack();
			firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//JPanel stuff
			JPanel panel = new JPanel();
	//		panel.setLayout(null);
			
			
			
			//Clicking Start button or <Enter> starts game
			JButton name = new JButton("Start");
			JTextField nameText = new JTextField(10);
			class firstWalkListener implements KeyListener{
				Timer jump;
				boolean w = false;
				boolean a = false;
				boolean s = false;
				boolean d = false;
				public void keyTyped(KeyEvent e) {}
				public void keyPressed(KeyEvent e) {
					if(e.getKeyChar()=='d') {
						d = true;
					}
					if (e.getKeyChar()=='a') {
						a = true;	
					}
					if (e.getKeyChar()=='w') {
						w = true;
					}
					if (e.getKeyChar()=='s') {
						playgame.crouchSonic();
						s = true;
					}
					if(e.getKeyChar()=='p') {
						JDialog pauseMenu = new JDialog();
						pauseMenu.setTitle("Scores");
						pauseMenu.setSize(new Dimension(400,200));
						JLabel coins = new JLabel("Rings: "+Integer.toString(playgame.getCoins())+"                  Score: "+Integer.toString(playgame.getUpdatedScore()));
						pauseMenu.add(coins);
						pauseMenu.setLocationRelativeTo(secondFrame);
						pauseMenu.setModal(true);
				        pauseMenu.setVisible(true);
					}
					if (s==true && d == true) {
						playgame.spinSonicXF();
					}
					if (s==true && a == true) {
						playgame.spinSonicXB();
					}
					if (s==false && d == true) {
						playgame.jogSonicXF();
					}
					if(s== false && a == true) {
						playgame.jogSonicXB();
					}
					if(s == true&&a==false&&d==false) {
						playgame.crouchSonic();
					}
					if (w==true&&a==true) {
						playgame.leapSonicB();
					}
					if (w==true&&d==true) {
						playgame.leapSonicF();
					}
					if (w == true&&a==false&&d==false) {
						playgame.jumpSonic();
					}
				}	
				public void keyReleased(KeyEvent e) {
					a = false;
					s = false;
					d = false;
					w = false;
					playgame.standStillSonic();
				}
			}
			
			
			
			
			JLabel instructions1 = new JLabel("Welcome to this Sonic The Hedgehog Game.");
			JLabel instructions2 = new JLabel("To control Sonic, use the WASD controls.");
			JLabel instructions3 = new JLabel("D makes him move right, ");
			JLabel instructions4 = new JLabel("A makes him move left,");
			JLabel instructions5 = new JLabel("S makes him crouch and W makes him jump.");
			JLabel instructions6 = new JLabel("Using S+(D or A) activates his spin dash.");
			JLabel instructions7 = new JLabel("Using W+ (D or A) makes him leap forward or backward.");
			JLabel instructions8 = new JLabel("Leap or dash into enemies to kill them.");
			JLabel instructions9 = new JLabel("Avoid spikes and springs.");
			JLabel instructions10 = new JLabel("Collect as many rings as you can as well.");	
			JLabel instructions11 = new JLabel("Killing enemies counts as 20 points.");		
			JLabel instructions12 = new JLabel("Collecting coins counts as 10 points but");
			JLabel instructions13 = new JLabel("they are added to the score at the end of the game.");
			JLabel instructions14 = new JLabel("Press P to bring up your score menu.");
			JLabel blank = new JLabel("                                ");
			JLabel enterName = new JLabel("Enter your name and then start the game");
			class startListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					String playerName = nameText.getText();
					firstFrame.dispose();
					panel.remove(enterName);
					panel.remove(nameText);
					panel.remove(name);
					playgame.addKeyListener(new firstWalkListener());
					playgame.setFocusable(true);
					secondFrame.requestFocus();
					playgame.setPreferredSize(new Dimension(X_DIMENSION-2,Y_DIMENSION-30));
					secondFrame.setResizable(false);
					secondFrame.add(playgame);
					secondFrame.pack();
					secondFrame.setVisible(true);
					secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					class closeGameListener implements ActionListener{
						boolean runThisOnce = true;
						public void actionPerformed (ActionEvent e) {
							if (playgame.closeGame()&&runThisOnce) {
								runThisOnce = false;
								secondFrame.dispose();
								JPanel endPanel = new JPanel();
								JFrame thirdFrame = new JFrame ("End Screen");
								JLabel endingMessage;
								if(playgame.winGame()) {
									endingMessage = new JLabel("Congratulations "+playerName+"! You won with a score of "+playgame.getUpdatedScore()+" and ended up collecting "+playgame.getCoins()+" coins! Would you like to play again?");
								}
								else {
									endingMessage = new JLabel("Ouch, tough luck, "+playerName+", you lost. You ended up with a score of "+playgame.getUpdatedScore()+". Would you like to play again?");
								}
								JButton yes = new JButton("Yes");
								class yesListener implements ActionListener{
									public void actionPerformed(ActionEvent e) {
										runGame();
										thirdFrame.dispose();
									}
								}
								yes.addActionListener(new yesListener());
								JButton no = new JButton("No");
								class noListener implements ActionListener{
									public void actionPerformed(ActionEvent e) {
										System.exit(0);
									}
								}
								no.addActionListener(new noListener());
								endPanel.add(endingMessage);
								endPanel.add(yes);
								endPanel.add(no);
								thirdFrame.setPreferredSize(new Dimension(X_DIMENSION, Y_DIMENSION));
								thirdFrame.pack();
								thirdFrame.setResizable(false);
								thirdFrame.add(endPanel);
								thirdFrame.setVisible(true);
								thirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							}
						}
					}
					
					Timer closeGame = new Timer(16,new closeGameListener());
					closeGame.start();
				}
				
			}
			name.addActionListener(new startListener());
			nameText.addActionListener(new startListener());
			name.setLocation(0, 0);
			
			
			
			class obstacleListener implements ActionListener{
				public void actionPerformed (ActionEvent e) {
					playgame.obstacle();
				}
			}
			Timer t = new Timer(16, new obstacleListener());
			t.start();
			
			panel.add(instructions1);
			panel.add(instructions2);
			panel.add(instructions3);
			panel.add(instructions4);
			panel.add(instructions5);
			panel.add(instructions6);
			panel.add(instructions7);
			panel.add(instructions8);
			panel.add(instructions9);
			panel.add(instructions10);
			panel.add(instructions11);
			panel.add(instructions12);
			panel.add(instructions13);
			panel.add(instructions14);
			panel.add(blank);
			panel.add(enterName);
			panel.add(nameText);
			panel.add(name);
			firstFrame.add(panel);
			firstFrame.setResizable(false);
			firstFrame.setVisible(true);
		}
	}
}
