import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

public class GameBoard extends JPanel{
	/*
		usage:
		new GameBoard (x coordinate, y coordinate, scale of x axis, scale of y axis)
		
		example code:
		
		JFrame window = new JFrame();
		GameBoard gb = new GameBoard(0, 0, 0.63, 0.63);
		window.add(gb, BorderLayout.CENTER);

	*/

	private int shiftX = 0;
	private int shiftY = 0;

	private double scaleX = 1;
	private double scaleY = 1;
	
	private double rectWidthHorizontal = 150;
	private double rectHeightHorizontal = 100;
	private double rectWidthVertical = 100;
	private double rectHeightVertical = 150;
	private double cornerWidth = 200;
	private double cornerHeight = 200;
	
	private double subRectWidthHorizontal = 30;
	private double subRectHeightHorizontal = rectHeightHorizontal;
	private double subRectWidthVertical = rectWidthVertical;
	private double subRectHeightVertical = 30;
	
	private double boardWidth = cornerWidth * 2 + rectWidthVertical * 8;
	private double boardHeight = cornerHeight * 2 + rectHeightHorizontal * 8;
	
	public GameBoard(int x, int y, double scaleX, double scaleY){
		shiftX = x;
		shiftY = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		
		rectWidthHorizontal = 150 * scaleX;
		rectHeightHorizontal = 100 * scaleY;
		rectWidthVertical = 100 * scaleX;
		rectHeightVertical = 150 * scaleY;
		cornerWidth = 200 * scaleX;
		cornerHeight = 200 * scaleY;
		
		subRectWidthHorizontal = 30 * scaleX;
		subRectHeightHorizontal = rectHeightHorizontal;
		subRectWidthVertical = rectWidthVertical;
		subRectHeightVertical = 30 * scaleY;
		
		boardWidth = cornerWidth * 2 + rectWidthVertical * 8;
		boardHeight = cornerHeight * 2 + rectHeightHorizontal * 8;

	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    drawBasicBoard(g);
	    drawSubRect(g);
	    drawText(g, "Oakland Oligarchy", boardWidth / 2.0 - 200, boardHeight / 2.0, new Font("TimesNewRoman", Font.BOLD, 50));
	    drawPlayerOnBottomRow(g, 0, 0);
	    drawPlayerOnCorner(g, 0, 0);
	    drawPlayerOnTopRow(g, 0, 0);
	    drawPlayerOnLeftRow(g, 0, 0);
	    drawPlayerOnRightRow(g, 0, 0);
	    
	}
	
	private void drawCorner(Graphics g, double x, double y){
		g.drawRect( (int) x,  (int) y, (int) cornerWidth, (int) cornerHeight);
	}
	
	private void drawVerticalBox(Graphics g, double x, double y){
		g.drawRect( (int) x,  (int) y,  (int) rectWidthVertical, (int) rectHeightVertical);
	}
	
	private void drawHorizontalBox(Graphics g, double x, double y){
		g.drawRect( (int) x, (int) y, (int) rectWidthHorizontal, (int) rectHeightHorizontal);
	}
	
	private void drawVerticalSubBox(Graphics g, double x, double y){
		g.setColor(Color.CYAN);
		g.fillRect( (int) x,  (int) y,  (int) subRectWidthVertical, (int) subRectHeightVertical);
	}
	
	private void drawHorizontalSubBox(Graphics g, double x, double y){
		g.setColor(Color.YELLOW);
		g.fillRect( (int) x, (int) y, (int) subRectWidthHorizontal, (int) subRectHeightHorizontal);
	}
	
	private void drawText(Graphics g, String text, double x, double y, Font font){
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(text, (int) x + shiftX, (int) y + shiftY);
	}
	
	private void drawBasicBoard(Graphics g){
		// top left corner
	    drawCorner(g, 0 + shiftX, 0 + shiftY);
	    
	    // top row
	    for(int i = 0; i < 8; i++)
	    	drawVerticalBox(g, (cornerWidth + i * rectWidthVertical) + shiftX, 0 + shiftY);
	    
	    // top right corner
	    drawCorner(g, (boardWidth - cornerWidth) + shiftX, 0  + shiftY);
	    
	    // left row
	    for(int i = 0; i < 8; i++)
	    	drawHorizontalBox(g, 0 + shiftX, (cornerHeight + i * rectHeightHorizontal)+ shiftY);
	    
	    // bottom left corner
	    drawCorner(g, 0 + shiftX, (boardHeight - cornerHeight) +  + shiftY);
	    
	    // right row
	    for(int i = 0; i < 8; i++)
	    	drawHorizontalBox(g, (boardWidth - rectWidthHorizontal) + shiftX, (cornerHeight + i * rectHeightHorizontal) + shiftY);
	    
	    // bottom right corner
	    drawCorner(g, (boardWidth - cornerWidth) + shiftX, (boardHeight - cornerHeight) + shiftY);
	    
	    // bottom row
	    for(int i = 0; i < 8; i++)
	    	drawVerticalBox(g, (cornerWidth + i * rectWidthVertical) + shiftX, (boardHeight - rectHeightVertical) + shiftY);
		
		
	}
	
	private void drawSubRect(Graphics g){
		 // top row
	    for(int i = 0; i < 8; i++)
	    	drawVerticalSubBox(g, (cornerWidth + i * rectWidthVertical) + shiftX, (rectHeightVertical - subRectHeightVertical) + shiftY);
	    
	    // left row
	    for(int i = 0; i < 8; i++)
	    	drawHorizontalSubBox(g, (rectWidthHorizontal - subRectWidthHorizontal) + shiftX, (cornerHeight + i * rectHeightHorizontal)+ shiftY);
	    
	    // right row
	    for(int i = 0; i < 8; i++)
	    	drawHorizontalSubBox(g, (boardWidth - rectWidthHorizontal) + shiftX + 1, (cornerHeight + i * rectHeightHorizontal) + shiftY);
	    
	    // bottom row
	    for(int i = 0; i < 8; i++)
	    	drawVerticalSubBox(g, (cornerWidth + i * rectWidthVertical) + shiftX, (boardHeight - rectHeightVertical) + shiftY + 1);		
		
	}
	
	private void drawPlayerOnCorner(Graphics g, int playerNum, int boxID){	
		int playerIconWidth = 50;
		int playerIconHeight = 50;
		int fixedValueX = 15;
		int fixedValueY = 15;
		
		// top left
		g.setColor(Color.red);
		g.fillRect((int)(boardWidth - cornerWidth) + shiftX + fixedValueX, (int)(boardHeight - (cornerHeight)) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// bottom right
		g.setColor(Color.orange);
		g.fillRect((int)(boardWidth - playerIconWidth) + shiftX - fixedValueX, (int)(boardHeight - (playerIconHeight)) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// bottom left
		g.setColor(Color.green);
		g.fillRect((int)(boardWidth - cornerWidth) + shiftX + fixedValueX, (int)(boardHeight - (playerIconHeight)) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// top right
		g.setColor(Color.blue);
		g.fillRect((int)(boardWidth - playerIconWidth) + shiftX - fixedValueX, (int)(boardHeight - (cornerHeight)) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
	}
	
	private void drawPlayerOnBottomRow(Graphics g, int playerNum, int boxID){
		int playerIconWidth = 50;
		int playerIconHeight = 50;
		int fixedValueX = 5;
		int fixedValueY = 5;
				
		// top left
		g.setColor(Color.red);
		g.fillRect((int)(boardWidth - (rectWidthVertical) - cornerWidth) + shiftX + fixedValueX, (int)(boardHeight - (rectHeightVertical) + subRectHeightVertical) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// bottom right
		g.setColor(Color.orange);
		g.fillRect((int)(boardWidth - playerIconWidth - cornerWidth) + shiftX - fixedValueX, (int)(boardHeight - (playerIconHeight)) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// bottom left
		g.setColor(Color.green);
		g.fillRect((int)(boardWidth - (rectWidthVertical) - cornerWidth) + shiftX + fixedValueX, (int)(boardHeight - (playerIconHeight)) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// top right
		g.setColor(Color.blue);
		g.fillRect((int)(boardWidth - playerIconWidth - cornerWidth) + shiftX - fixedValueX, (int)(boardHeight - (rectHeightVertical) + subRectHeightVertical) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);	
		
	}
	
	private void drawPlayerOnTopRow(Graphics g, int playerNum, int boxID){
		int playerIconWidth = 50;
		int playerIconHeight = 50;
		int fixedValueX = 5;
		int fixedValueY = 5;
				
		// top left
		g.setColor(Color.red);
		g.fillRect((int)(boardWidth - (rectWidthVertical) - cornerWidth) + shiftX + fixedValueX, (int)(rectHeightVertical - subRectHeightVertical - playerIconHeight) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// bottom right
		g.setColor(Color.orange);
		g.fillRect((int)(boardWidth - playerIconWidth - cornerWidth) + shiftX - fixedValueX, (int)(0) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// bottom left
		g.setColor(Color.green);
		g.fillRect((int)(boardWidth - (rectWidthVertical) - cornerWidth) + shiftX + fixedValueX, (int)(0) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// top right
		g.setColor(Color.blue);
		g.fillRect((int)(boardWidth - playerIconWidth - cornerWidth) + shiftX - fixedValueX, (int)(rectHeightVertical - subRectHeightVertical - playerIconHeight) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);			
		
	}

	private void drawPlayerOnLeftRow(Graphics g, int playerNum, int boxID){
		int playerIconWidth = 50;
		int playerIconHeight = 50;
		int fixedValueX = 5;
		int fixedValueY = 5;
				
		// top left
		g.setColor(Color.red);
		g.fillRect((int)(boardWidth - rectWidthHorizontal + subRectWidthHorizontal) + shiftX + fixedValueX, (int)(rectHeightHorizontal - playerIconHeight + cornerHeight) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// bottom right
		g.setColor(Color.orange);
		g.fillRect((int)(rectWidthHorizontal - subRectWidthHorizontal - playerIconWidth) + shiftX - fixedValueX, (int)(cornerHeight) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// bottom left
		g.setColor(Color.green);
		g.fillRect((int)(boardWidth - rectWidthHorizontal + subRectWidthHorizontal) + shiftX + fixedValueX, (int)(cornerHeight) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// top right
		g.setColor(Color.blue);
		g.fillRect((int)(boardWidth - playerIconWidth) + shiftX - fixedValueX, (int)(rectHeightHorizontal - playerIconHeight + cornerHeight) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);			
			
	
	}
	
	private void drawPlayerOnRightRow(Graphics g, int playerNum, int boxID){
		int playerIconWidth = 50;
		int playerIconHeight = 50;
		int fixedValueX = 5;
		int fixedValueY = 5;
				
		// top left
		g.setColor(Color.red);
		g.fillRect((int)(0) + shiftX + fixedValueX, (int)(rectHeightHorizontal - playerIconHeight + cornerHeight) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);
		// bottom right
		g.setColor(Color.orange);
		g.fillRect((int)(boardWidth - playerIconWidth) + shiftX - fixedValueX, (int)(cornerHeight) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// bottom left
		g.setColor(Color.green);
		g.fillRect((int)(0) + shiftX + fixedValueX, (int)(cornerHeight) + shiftY + fixedValueY, playerIconWidth, playerIconHeight);
		// top right
		g.setColor(Color.blue);
		g.fillRect((int)(rectWidthHorizontal - subRectWidthHorizontal - playerIconWidth) + shiftX - fixedValueX, (int)(rectHeightHorizontal - playerIconHeight + cornerHeight) + shiftY - fixedValueY, playerIconWidth, playerIconHeight);			
				
		
	}
	
	public static void main(String args[]){
		// for testing
		
		JFrame newFrame = new JFrame("Board");
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.setSize(2000,2000);
		
		GameBoard gb = new GameBoard(5, 5, 1.5, 1.5);
		newFrame.add(gb, BorderLayout.CENTER);
		
		newFrame.setVisible(true);
		
		
	}
}
