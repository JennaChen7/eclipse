class DrawingArea extends javax.swing.JPanel implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener, java.awt.event.KeyListener, java.awt.event.ComponentListener
{
	private int maxWidth, maxHeight;
	private java.awt.image.BufferedImage bi;
	private java.awt.Color backgroundColor, foregroundColor, rectangleColor, circleColor;
	private java.awt.Graphics2D biG;
	private int imageWidth;
	private int imageHeight;
	private int lastX;
	private int lastY;
	
	public DrawingArea()
	{
		java.awt.Dimension maxDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		maxWidth = (int) Math.max(2592, maxDim.getWidth());
		maxHeight = (int) Math.max(1944, maxDim.getHeight());
		setPreferredSize(new java.awt.Dimension(maxWidth, maxHeight));
		bi = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(maxWidth, maxHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
		biG = bi.createGraphics();
		
		foregroundColor = new java.awt.Color(0, 0, 0, 255);
		backgroundColor = new java.awt.Color(255, 255, 255, 255);
		rectangleColor = new java.awt.Color(255, 0, 0, 255);
		circleColor = new java.awt.Color(0, 255, 0, 255);
		lastX = -1;
		lastY = -1;
		
		biG.setColor(backgroundColor);
		biG.fillRect(0, 0, maxWidth, maxHeight);
		biG.setColor(foregroundColor);
		
		repaint(0);		
	}
			
	public void componentHidden(java.awt.event.ComponentEvent e)
	{
		System.out.println("componentHidden");
	}
	
	public void componentShown(java.awt.event.ComponentEvent e)
	{
		System.out.println("componentShown");
	}
	
	public void componentMoved(java.awt.event.ComponentEvent e)
	{
		System.out.println("componentMoved");
	}
	
	public void componentResized(java.awt.event.ComponentEvent e)
	{
		System.out.println("componentResized");
	}
	
	public void mouseEntered(java.awt.event.MouseEvent e)
	{
		System.out.println("mouseEntered");
	}
	
	public void mouseExited(java.awt.event.MouseEvent e)
	{
		System.out.println("mouseExited");
	}
	
	public void mousePressed(java.awt.event.MouseEvent e)
	{
		lastX = e.getX();
		lastY = e.getY();
		System.out.println("mousePressed  (" + e.getX() + "," + e.getY() + ")");
	}
	
	public void mouseReleased(java.awt.event.MouseEvent e)
	{
		System.out.println("mouseReleased  (" + e.getX() + "," + e.getY() + ")");
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e)
	{
		System.out.println("mouseClicked (" + e.getX() + "," + e.getY() + ")");
	}
	
	public void mouseDragged(java.awt.event.MouseEvent e)
	{
		System.out.println("mouseDragged (" + e.getX() + "," + e.getY() + ")");
		int x = e.getX();
		int y = e.getY();
		
		if( (lastX >= 0) && (lastY >= 0) )
		{
			biG.drawLine(lastX, lastY, x, y);
			lastX = x;
			lastY = y;
			repaint(0);
		}
		else
		{
			lastX = x;
			lastY = y;
		}
	}
	
	public void mouseMoved(java.awt.event.MouseEvent e)
	{
		System.out.println("mouseMoved (" + e.getX() + "," + e.getY() + ")");
	}
	
	public void keyPressed(java.awt.event.KeyEvent e)
	{
		System.out.println("keyPressed");
		if( e.getKeyCode() == e.VK_C )
		{
			biG.setColor(backgroundColor);
			biG.fillRect(0, 0, maxWidth, maxHeight);
			biG.setColor(foregroundColor);
			lastX = -1;
			lastY = -1;
			
			repaint(0);	
		}
		
		if( e.getKeyCode() == e.VK_R )
		{
			if( (lastX >= 0) && (lastY >= 0) )
			{
				biG.setColor(rectangleColor);
				biG.drawRect(lastX-10, lastY-10, 20, 20);
				biG.setColor(foregroundColor);
				repaint(0);
			}
		}
		
		if( e.getKeyCode() == e.VK_O )
		{
			if( (lastX >= 0) && (lastY >= 0) )
			{
				biG.setColor(circleColor);
				biG.drawOval(lastX-10, lastY-10, 20, 20);
				biG.setColor(foregroundColor);
				repaint(0);
			}
		}
		
	}
	
	public void keyReleased(java.awt.event.KeyEvent e)
	{
		System.out.println("keyReleased");
	}
	
	public void keyTyped(java.awt.event.KeyEvent e)
	{
		System.out.println("keyTyped");
	}
	
	public void paintComponent(java.awt.Graphics g)
	{
		super.paintComponent(g);
		if( bi == null )
		{
			return;
		}
		g.drawImage(bi, 0, 0, null);
	}
}
