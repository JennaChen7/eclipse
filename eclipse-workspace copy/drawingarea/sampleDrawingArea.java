class sampleDrawingArea
{
	private static int defaultWidth = 600;
	private static int defaultHeight = 600;
	
	public static void main(String[] args)
	{
		int width;
		int height;
		DrawingArea da;
		javax.swing.JFrame f;
		javax.swing.JPanel daPanel;
		javax.swing.JPanel mainPanel;
		javax.swing.JScrollPane scrollPane;
		
		width = defaultWidth;
		height = defaultHeight;
		
		if( args.length > 2 )
		{
			System.out.println("format: sampleDrawingArea <width> <height>");
			System.exit(0);
		}
		
		try
		{
			if( args.length > 0 )
			{
				width = Integer.parseInt(args[0]);
			}
			
			if( args.length > 1 )
			{
				height = Integer.parseInt(args[1]);
			}
			
		}
		catch(Exception e)
		{
		}
		
		f = new javax.swing.JFrame();
		da = new DrawingArea();
		
		daPanel = new javax.swing.JPanel();
		daPanel.setLayout(new java.awt.BorderLayout());
		mainPanel = new javax.swing.JPanel();
		mainPanel.setLayout(new java.awt.BorderLayout());
		daPanel.add(da, java.awt.BorderLayout.CENTER);
		daPanel.validate();
		daPanel.setVisible(true);
		
		scrollPane = new javax.swing.JScrollPane(daPanel);
		scrollPane.validate();
		scrollPane.setVisible(true);
		mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
		
		f.setPreferredSize(new java.awt.Dimension(width, height));
		f.setMinimumSize(new java.awt.Dimension(width, height));
		f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
		f.setContentPane(mainPanel);
		daPanel.addMouseListener(da);
		daPanel.addMouseMotionListener(da);
		f.validate();
		f.setVisible(true);
		f.addComponentListener(da);
		f.addKeyListener(da);
		f.addMouseListener(da);
		f.addMouseMotionListener(da);
	}
}
