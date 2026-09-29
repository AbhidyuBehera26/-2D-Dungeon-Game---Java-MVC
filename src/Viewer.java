import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.GameObject;


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
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	private Model gameworld = new Model(); 
	private HashMap<String, BufferedImage> imageCache = new HashMap<>();
	 
	public Viewer(Model World) {
		this.gameworld=World;
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
	}

	public Viewer(LayoutManager layout) {
		super(layout);
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		CurrentAnimationTime++; 
		
		// Draw background 
		drawBackground(g);
		
	if (gameworld.isGameOver()) {
			drawGameOver(g);
			return;
		}
		
		// Draw Level Transition Hole
		GameObject hole = gameworld.getNextLevelHole();
		if (hole != null) {
			drawEnemies(hole, g);
		}

		// Draw Player 1
		GameObject p1 = gameworld.getPlayer();
		drawPlayer(p1, g);
		
		// Draw Player 2
		GameObject p2 = gameworld.getPlayer2();
		drawPlayer(p2, g);

		// Draw Bullets 
		gameworld.getBullets().forEach((temp) -> { 
			drawBullet(temp, g);	 
		}); 
		
		// Draw Enemies   
		gameworld.getEnemies().forEach((temp) -> {
			drawEnemies(temp, g);	 
	    }); 
		
		// Draw Potions
		gameworld.getPotions().forEach((temp) -> {
			drawEnemies(temp, g);
		});
		
		// Draw HUD
		drawHUD(g);
	}

	private void drawHUD(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(24.0f));
		g.drawString("Level: " + gameworld.getLevel(), 20, 30);
		g.drawString("Score: " + gameworld.getScore(), 20, 60);
		g.drawString("Lives: " + gameworld.getLives(), 20, 90);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("[F5] Save | [F9] Load", 20, 120);
		g.setColor(Color.YELLOW);
		g.drawString("P1: WASD | LEFT CLICK / SPACE (Sword Slash)", 20, 930);
		g.setColor(Color.CYAN);
		g.drawString("P2: Arrows | ENTER (Sword Slash)", 20, 960);
	}

	private void drawGameOver(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(64.0f));
		g.drawString("GAME OVER", 300, 500);
		g.setFont(g.getFont().deriveFont(24.0f));
		g.setColor(Color.WHITE);
		g.drawString("Final Score: " + gameworld.getScore(), 400, 550);
	}
	
	private void drawEnemies(GameObject obj, Graphics g) {
		drawAnimatedSprite(obj, g);
	}

	private BufferedImage getImage(String filename) {
		if (imageCache.containsKey(filename)) {
			return imageCache.get(filename);
		}
		
		// Try multiple possible relative paths
		String[] possiblePaths = {
			filename,
			"BasicGameTemplate/" + filename,
			"BasicGameTemplate Final/BasicGameTemplate/" + filename,
			"../" + filename,
			"../../" + filename
		};
		
		for (String path : possiblePaths) {
			File f = new File(path);
			if (f.exists()) {
				try {
					BufferedImage img = ImageIO.read(f);
					imageCache.put(filename, img);
					return img;
				} catch (IOException e) {
					// Continue to next path
				}
			}
		}
		
		// If all fail, print a warning to help find the issue
		System.out.println("MISSING IMAGE: " + filename + " (Check if it exists in your res folder)");
		return null;
	}

	private void drawBackground(Graphics g)
	{
		BufferedImage myImage = getImage("res/realistic_dungeon.png");
		if (myImage != null) {
			g.drawImage(myImage, 0, 0, 1000, 1000, null); 
		}
	}
	
	private void drawBullet(GameObject temp, Graphics g)
	{
		BufferedImage myImage = getImage(temp.getTexture()); 
		if (myImage != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.translate((int) temp.getCentre().getX(), (int) temp.getCentre().getY());
			g2d.rotate(Math.toRadians(temp.getRotation()));
			g2d.drawImage(myImage, -temp.getWidth()/2, -temp.getHeight()/2, temp.getWidth(), temp.getHeight(), null); 
			g2d.dispose();
		}
	}
	

	private void drawPlayer(GameObject player, Graphics g) { 
		drawAnimatedSprite(player, g);
	}

	private void drawAnimatedSprite(GameObject obj, Graphics g) {
		BufferedImage myImage = getImage(obj.getTexture());
		int x = (int) obj.getCentre().getX();
		int y = (int) obj.getCentre().getY();
		int width = obj.getWidth();
		int height = obj.getHeight();
		
		if (myImage != null) {
			boolean isAnimated = obj.getTexture().contains("knight") || obj.getTexture().contains("goblin") || obj.getTexture().contains("skeleton");
			
			if (isAnimated) {
				int frames = 4; // Our sheets have 4 frames
				int frameWidth = myImage.getWidth() / frames;
				if (frameWidth <= 0) frameWidth = 1;
				int frameHeight = myImage.getHeight();
				
				int currentFrame = 0;
				boolean isMoving = true;
				if (obj.getTexture().contains("red_knight")) {
					isMoving = Controller.getInstance().isKeyAPressed() || Controller.getInstance().isKeyDPressed() || Controller.getInstance().isKeyWPressed() || Controller.getInstance().isKeySPressed();
				} else if (obj.getTexture().contains("blue_knight")) {
					isMoving = Controller.getInstance().isKeyLeftPressed() || Controller.getInstance().isKeyRightPressed() || Controller.getInstance().isKeyUPPressed() || Controller.getInstance().isKeyDownPressed();
				}
				
				if (isMoving) {
					currentFrame = (int) ((CurrentAnimationTime % 40) / 10); 
				}
				
				int sx = currentFrame * frameWidth;
				
				// Preserve the aspect ratio of the original sprite
				int drawW = width;
				int drawH = (int) (((double) frameHeight / frameWidth) * width);
				
				if (obj.isFacingRight()) {
					g.drawImage(myImage, x - drawW/2, y - drawH/2, x + drawW/2, y + drawH/2, sx, 0, sx + frameWidth, frameHeight, null);
				} else {
					// Flip horizontally by swapping sx rect bounds
					g.drawImage(myImage, x - drawW/2, y - drawH/2, x + drawW/2, y + drawH/2, sx + frameWidth, 0, sx, frameHeight, null);
				}
			} else {
				g.drawImage(myImage, x - width/2, y - height/2, width, height, null);
			}
		}
	}
		 
	 

}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
