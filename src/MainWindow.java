import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.UnitTests;

/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */ 



public class MainWindow {
	 private static  JFrame frame = new JFrame("Game");   // Change to the name of your game 
	 private static   Model gameworld= new Model();
	 private static   Viewer canvas = new  Viewer( gameworld);
	 private Controller controller = Controller.getInstance(); 
	 private static   int TargetFPS = 100;
	 private static volatile boolean startGame= false; 
	 private   JLabel BackgroundImageForStartMenu ;
	 
	 private static JButton returnMenuButton;
	 private static JButton exitButton;
	  
	public MainWindow() {
	        frame.setSize(1000, 1000);  
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	        frame.setLayout(null);
	        frame.add(canvas);  
	        canvas.setBounds(0, 0, 1000, 1000); 
			canvas.setBackground(new Color(255,255,255)); 
		    canvas.setVisible(false);   
		    canvas.setLayout(null);
		    
	        returnMenuButton = new JButton("Main Menu");
	        returnMenuButton.setBounds(250, 650, 200, 60);
	        returnMenuButton.setBackground(Color.DARK_GRAY);
	        returnMenuButton.setForeground(Color.CYAN);
	        returnMenuButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 24));
	        returnMenuButton.setFocusPainted(false);
	        returnMenuButton.setVisible(false);
	        
	        returnMenuButton.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		frame.dispose();
	        		frame = new JFrame("Game");
	        		gameworld = new Model();
	        		canvas = new Viewer(gameworld);
	        		startGame = false;
	        		new MainWindow();
	        	}
	        });
	        
	        exitButton = new JButton("Exit Game");
	        exitButton.setBounds(550, 650, 200, 60);
	        exitButton.setBackground(Color.DARK_GRAY);
	        exitButton.setForeground(Color.RED);
	        exitButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 24));
	        exitButton.setFocusPainted(false);
	        exitButton.setVisible(false);
	        exitButton.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		System.exit(0);
	        	}
	        });
	        
	        canvas.add(returnMenuButton);
	        canvas.add(exitButton);
		          
		       
	        JButton normalButton = new JButton("Normal");
	        normalButton.setBounds(350, 350, 300, 60); 
	        normalButton.setBackground(Color.DARK_GRAY);
	        normalButton.setForeground(Color.GREEN);
	        normalButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 32));
	        normalButton.setFocusPainted(false);
	        normalButton.setVisible(false);
	        
	        JButton hardButton = new JButton("Hard");
	        hardButton.setBounds(350, 450, 300, 60); 
	        hardButton.setBackground(Color.DARK_GRAY);
	        hardButton.setForeground(Color.YELLOW);
	        hardButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 32));
	        hardButton.setFocusPainted(false);
	        hardButton.setVisible(false);
	        
	        JButton nightmareButton = new JButton("Nightmare");
	        nightmareButton.setBounds(350, 550, 300, 60); 
	        nightmareButton.setBackground(Color.DARK_GRAY);
	        nightmareButton.setForeground(Color.RED);
	        nightmareButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 32));
	        nightmareButton.setFocusPainted(false);
	        nightmareButton.setVisible(false);
	        
	        JButton newGameButton = new JButton("New Game"); 
	        newGameButton.setBounds(350, 450, 300, 60); 
	        newGameButton.setBackground(Color.DARK_GRAY);
	        newGameButton.setForeground(Color.RED);
	        newGameButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 32));
	        newGameButton.setFocusPainted(false);
	        newGameButton.setVisible(false);
	        
	        JButton loadMenuButton = new JButton("Saved Game"); 
	        loadMenuButton.setBounds(350, 550, 300, 60); 
	        loadMenuButton.setBackground(Color.DARK_GRAY);
	        loadMenuButton.setForeground(Color.CYAN);
	        loadMenuButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 32));
	        loadMenuButton.setFocusPainted(false);
	        loadMenuButton.setVisible(false);
	        
	        JButton startGameButton = new JButton("Start Game"); 
	        startGameButton.setBounds(350, 800, 300, 60); 
	        startGameButton.setBackground(Color.DARK_GRAY);
	        startGameButton.setForeground(Color.WHITE);
	        startGameButton.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 32));
	        startGameButton.setFocusPainted(false);
	        
	        File saveFile = new File("savegame.txt");
	        if (!saveFile.exists()) {
	        	loadMenuButton.setEnabled(false); // Disable if no save exists
	        }
	        
	        startGameButton.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) { 
					startGameButton.setVisible(false);
					newGameButton.setVisible(true);
					loadMenuButton.setVisible(true);
				}}); 

	        loadMenuButton.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					gameworld.loadGame();
					newGameButton.setVisible(false);
					loadMenuButton.setVisible(false);
					startGame(BackgroundImageForStartMenu);
				}}); 

	        newGameButton.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) { 
					newGameButton.setVisible(false);
					loadMenuButton.setVisible(false);
					normalButton.setVisible(true);
					hardButton.setVisible(true);
					nightmareButton.setVisible(true);
				}}); 

	        normalButton.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) { 
					gameworld.setDifficulty("Normal");
					normalButton.setVisible(false); hardButton.setVisible(false); nightmareButton.setVisible(false);
					startGame(BackgroundImageForStartMenu);
				}});  
	        
	        hardButton.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) { 
					gameworld.setDifficulty("Hard");
					normalButton.setVisible(false); hardButton.setVisible(false); nightmareButton.setVisible(false);
					startGame(BackgroundImageForStartMenu);
				}});  
	        
	        nightmareButton.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) { 
					gameworld.setDifficulty("Nightmare");
					normalButton.setVisible(false); hardButton.setVisible(false); nightmareButton.setVisible(false);
					startGame(BackgroundImageForStartMenu);
				}});
	        
	        File BackroundToLoad = new File("res/startscreen.png");  
			try {
				 BufferedImage myPicture = ImageIO.read(BackroundToLoad);
				 Image scaledImage = myPicture.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
				 BackgroundImageForStartMenu = new JLabel(new ImageIcon(scaledImage));
				 BackgroundImageForStartMenu.setBounds(0, 0, 1000, 1000);
				frame.add(BackgroundImageForStartMenu); 
			}  catch (IOException e) { 
				e.printStackTrace();
			}   
			 
	         frame.add(startGameButton);
	         frame.add(newGameButton);
	         frame.add(loadMenuButton);
	         frame.add(normalButton);  
	         frame.add(hardButton);
	         frame.add(nightmareButton);

	       frame.setVisible(true);   
	}
	
	private void startGame(JLabel bg) {
		if (bg != null) bg.setVisible(false); 
		canvas.setVisible(true); 
		canvas.addKeyListener(controller);
		canvas.addMouseListener(controller);
		canvas.addMouseMotionListener(controller); 
		canvas.requestFocusInWindow();
		startGame = true;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainWindow());
		
		// Start game logic in a separate background thread to prevent UI freezing
		new Thread(() -> {
			while(true) 
			{ 
				int TimeBetweenFrames =  1000 / TargetFPS;
				long frameStartTime = System.currentTimeMillis();
				long FrameCheck = frameStartTime + (long) TimeBetweenFrames; 
				
				if(startGame)
				{
					gameloop();
				}
				
				// Stay at Target FPS. Thread.sleep is much better than a busy-wait for CPU health.
				long sleepTime = FrameCheck - System.currentTimeMillis();
				if (sleepTime > 0) {
					try { Thread.sleep(sleepTime); } catch (InterruptedException e) {}
				}
				
				UnitTests.CheckFrameRate(FrameCheck, System.currentTimeMillis(), TargetFPS); 
			}
		}).start();
	} 

	private static void gameloop() { 
		gameworld.gamelogic();
		
		SwingUtilities.invokeLater(() -> {
			if (gameworld.isGameOver()) {
				if (!exitButton.isVisible()) {
					exitButton.setVisible(true);
					returnMenuButton.setVisible(true);
					frame.repaint();
				}
			}
			
			if (canvas.getWidth() != frame.getWidth() || canvas.getHeight() != frame.getHeight()) {
			    canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			}
			canvas.updateview(); 
			frame.setTitle("Level = " + gameworld.getLevel() + "  |  Score =  " + gameworld.getScore() + "  |  Lives = " + gameworld.getLives()); 
		});
	}

}

/*
 * 
 * 

Hand shake agreement 
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,=+++
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,:::::,=+++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,:++++????+??
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,:,,:,:,,,,,,,,,,,,,,,,,,,,++++++?+++++????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=++?+++++++++++??????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++?+++?++?++++++++++?????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,~+++++++++++++++????+++++++???????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,:===+=++++++++++++++++++++?+++????????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,~=~~~======++++++++++++++++++++++++++????????????????
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,::::,,,,,,=~.,,,,,,,+===~~~~~~====++++++++++++++++++++++++++++???????????????
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,,~~.~??++~.,~~~~~======~=======++++++++++++++++++++++++++????????????????II
:::::::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,,:=+++??=====~~~~~~====================+++++++++++++++++++++?????????????????III
:::::::::::::::::::::::::::::::::::::::::::::::::::,:,,,++~~~=+=~~~~~~==~~~::::~~==+++++++==++++++++++++++++++++++++++?????????????????IIIII
::::::::::::::::::::::::::::::::::::::::::::::::,:,,,:++++==+??+=======~~~~=~::~~===++=+??++++++++++++++++++++++++?????????????????I?IIIIIII
::::::::::::::::::::::::::::::::::::::::::::::::,,:+????+==??+++++?++====~~~~~:~~~++??+=+++++++++?++++++++++??+???????????????I?IIIIIIII7I77
::::::::::::::::::::::::::::::::::::::::::::,,,,+???????++?+?+++???7?++======~~+=====??+???++++++??+?+++???????????????????IIIIIIIIIIIIIII77
:::::::::::::::::::::::::::::::::::::::,,,,,,=??????IIII7???+?+II$Z77??+++?+=+++++=~==?++?+?++?????????????III?II?IIIIIIIIIIIIIIIIIIIIIIIIII
::::::::::::::::::::::::::::::,,,,,,~=======++++???III7$???+++++Z77ZDZI?????I?777I+~~+=7+?II??????????????IIIIIIIIIIIIIIIIIIIIII??=:,,,,,,,,
::::::::,:,:,,,,,,,:::~==+=++++++++++++=+=+++++++???I7$7I?+~~~I$I??++??I78DDDO$7?++==~I+7I7IIIIIIIIIIIIIIIIII777I?=:,,,,,,,,,,,,,,,,,,,,,,,,
++=++=++++++++++++++?+????+??????????+===+++++????I7$$ZZ$I+=~$7I???++++++===~~==7??++==7II?~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+++++++++++++?+++?++????????????IIIII?I+??I???????I7$ZOOZ7+=~7II?+++?II?I?+++=+=~~~7?++:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
+?+++++????????????????I?I??I??IIIIIIII???II7II??I77$ZO8ZZ?~~7I?+==++?O7II??+??+=====.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
?????????????III?II?????I?????IIIII???????II777IIII7$ZOO7?+~+7I?+=~~+???7NNN7II?+=+=++,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
????????????IIIIIIIIII?IIIIIIIIIIII????II?III7I7777$ZZOO7++=$77I???==+++????7ZDN87I??=~,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIII?II??IIIIIIIIIIIIIIIIIIIIIIIIIII???+??II7777II7$$OZZI?+$$$$77IIII?????????++=+.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII?+++?IIIII7777$$$$$$7$$$$7IIII7I$IIIIII???I+=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII???????IIIIII77I7777$7$$$II????I??I7Z87IIII?=,,,,,,,,,,,:,,::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777777777777I7I777777777~,,,,,,,+77IIIIIIIIIII7II7$$$Z$?I????III???II?,,,,,,,,,,::,::::::::,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
777777777777$77777777777+::::::::::::::,,,,,,,=7IIIII78ZI?II78$7++D7?7O777II??:,,,:,,,::::::::::::::,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$$$$$$$$$$$77=:,:::::::::::::::::::::::::::,,7II$,,8ZZI++$8ZZ?+=ZI==IIII,+7:,,,,:::::::::::::::::,:::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
$$$I~::::::::::::::::::::::::::::::::::::::::::II+,,,OOO7?$DOZII$I$I7=77?,,,,,,:::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::+ZZ?,$ZZ$77ZZ$?,,,,,::::::::::::::::::::::::::,::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::I$:::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,:,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,,,
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::,,,,,,,,,,,,,,,,,,,,,,
                                                                                                                             GlassGiant.com
 * 
 * 
 */
