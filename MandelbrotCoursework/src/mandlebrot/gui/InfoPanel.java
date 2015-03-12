package mandlebrot.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mandelbrot.utilities.Pair;

class InfoPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private GUI gui;

	/**
	 * @param gui
	 */
	InfoPanel(GUI gui)
	{
		this.gui = gui;
	}

	private static final long serialVersionUID = -5157980355510237660L;

	private JFormattedTextField txtRealLower, txtImaginaryLower;
	private JFormattedTextField txtRealUpper, txtImaginaryUpper;
	private JButton btnChangeAxis;
	private JLabel lblRealBounds;
	private JLabel lblImaginaryBounds;
	private JLabel lblIterations;
	private JFormattedTextField txtIterations;
	private JButton btnSubmitIterations;
	private JButton btnRestoreDefault;
	private JLabel lblSelectedComplexPoint;
	private JComboBox<String> cmbJuliaFavourites;

	public void init()
	{
		gui.getPnlInfo().setLayout(new BoxLayout(gui.getPnlInfo(), BoxLayout.LINE_AXIS));

		lblRealBounds = new JLabel("Real Pair (x):     ");
		lblImaginaryBounds = new JLabel("Imaginary Pair (y):     ");
		lblIterations = new JLabel("Iterations:     ");
		lblSelectedComplexPoint = new JLabel("Selected point: ");

		/*
		 * try { txtRealLower = new JFormattedTextField(new MaskFormatter("##.##")); txtImaginaryLower = new
		 * JFormattedTextField(new MaskFormatter("##.##")); txtRealUpper = new JFormattedTextField(new MaskFormatter("##.##"));
		 * txtImaginaryUpper = new JFormattedTextField(new MaskFormatter("##.##"));
		 * 
		 * txtRealLower.setValue(new Double(-2)); txtImaginaryLower.setValue(new Double(-1.6)); txtRealUpper.setValue(new
		 * Double(2)); txtImaginaryUpper.setValue(new Double(1.6)); } catch (ParseException e) {
		 * System.err.println(e.getMessage()); e.printStackTrace(); }
		 */

		txtRealLower = new JFormattedTextField(new Double(GUI.DEFAULT_X_AXIS_COMPLEX.getLeft()));
		txtRealUpper = new JFormattedTextField(new Double(GUI.DEFAULT_X_AXIS_COMPLEX.getRight()));
		txtImaginaryLower = new JFormattedTextField(new Double(GUI.DEFAULT_Y_AXIS_COMPLEX.getLeft()));
		txtImaginaryUpper = new JFormattedTextField(new Double(GUI.DEFAULT_Y_AXIS_COMPLEX.getRight()));
		btnChangeAxis = new JButton("Submit New Axis");
		txtIterations = new JFormattedTextField(Integer.valueOf(GUI.DEFAULT_ITERATIONS));
		btnSubmitIterations = new JButton("Submit Iteration Amount");
		btnRestoreDefault = new JButton("Restore Defaults");

		if (GUI.IMAGE_DIRECTORY.list() != null)
		{
			String[] imageList = GUI.IMAGE_DIRECTORY.list();
			cmbJuliaFavourites = new JComboBox<String>(imageList);
		}
		else
			cmbJuliaFavourites = new JComboBox<String>();

		cmbJuliaFavourites.setSelectedItem(null);
		cmbJuliaFavourites.addActionListener(new ComboBoxListener());

		txtRealLower.setMargin(new Insets(5, 5, 5, 5));
		txtRealUpper.setMargin(new Insets(5, 5, 5, 5));
		txtImaginaryLower.setMargin(new Insets(5, 5, 5, 5));
		txtImaginaryUpper.setMargin(new Insets(5, 5, 5, 5));
		txtIterations.setMargin(new Insets(5, 5, 5, 5));

		txtRealLower.setPreferredSize(new Dimension(50, 25));
		txtRealUpper.setPreferredSize(new Dimension(50, 25));
		txtImaginaryLower.setPreferredSize(new Dimension(50, 25));
		txtImaginaryUpper.setPreferredSize(new Dimension(50, 25));
		btnChangeAxis.setPreferredSize(new Dimension(150, 25));
		txtIterations.setPreferredSize(new Dimension(50, 25));
		btnSubmitIterations.setPreferredSize(new Dimension(200, 25));

		txtRealLower.setMaximumSize(new Dimension(100, 25));
		txtRealUpper.setMaximumSize(new Dimension(100, 25));
		txtImaginaryLower.setMaximumSize(new Dimension(100, 25));
		txtImaginaryUpper.setMaximumSize(new Dimension(100, 25));
		btnChangeAxis.setMaximumSize(new Dimension(200, 25));
		txtIterations.setMaximumSize(new Dimension(100, 25));
		btnSubmitIterations.setMaximumSize(new Dimension(200, 25));

		gui.getPnlInfo().setBackground(Color.lightGray);
		gui.getPnlInfo().setPreferredSize(
				new Dimension(GUI.DEFAULT_FRAME_WIDTH, (int) (GUI.DEFAULT_FRAME_HEIGHT * 0.125)));
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(lblRealBounds);
		gui.getPnlInfo().add(txtRealLower);
		gui.getPnlInfo().add(new JLabel("  -  "));
		gui.getPnlInfo().add(txtRealUpper);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(lblImaginaryBounds);
		gui.getPnlInfo().add(txtImaginaryLower);
		gui.getPnlInfo().add(new JLabel("  -  "));
		gui.getPnlInfo().add(txtImaginaryUpper);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(btnChangeAxis);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(lblIterations);
		gui.getPnlInfo().add(txtIterations);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(btnSubmitIterations);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(btnRestoreDefault);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(lblSelectedComplexPoint);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(cmbJuliaFavourites);
		gui.getPnlInfo().add(Box.createHorizontalGlue());
		gui.getPnlInfo().add(new JLabel("Press P to save a Julia Image"));
		gui.getPnlInfo().add(Box.createHorizontalGlue());

		btnChangeAxis.addActionListener(this);
		btnSubmitIterations.addActionListener(this);
		btnRestoreDefault.addActionListener(this);
		gui.getPnlOuter().add(gui.getPnlInfo());
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		super.paintComponent(g);
	}

	public void updateBounds(Pair<Double, Double> xAxisComplex, Pair<Double, Double> yAxisComplex)
	{
		DecimalFormat dF = new DecimalFormat("#.##");
		
		txtRealLower.setText(dF.format(xAxisComplex.getLeft()));
		txtRealUpper.setText(dF.format(xAxisComplex.getRight()));
		txtImaginaryLower.setText(dF.format(yAxisComplex.getLeft()));
		txtImaginaryUpper.setText(dF.format(yAxisComplex.getRight()));
	}

	public void updateIterations(int iterations)
	{
		txtIterations.setText(String.valueOf(iterations));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnChangeAxis)
		{
			gui.setxAxisComplex(new Pair<Double, Double>(Double.parseDouble(txtRealLower.getText()), Double
					.parseDouble(txtRealUpper.getText())));
			gui.setyAxisComplex(new Pair<Double, Double>(Double.parseDouble(txtImaginaryLower.getText()), Double
					.parseDouble(txtImaginaryUpper.getText())));
		}
		else if (e.getSource() == btnSubmitIterations)
		{
			gui.setIterations(Integer.parseInt(txtIterations.getText()));
		}
		else if (e.getSource() == btnRestoreDefault)
		{
			updateBounds(GUI.DEFAULT_X_AXIS_COMPLEX, GUI.DEFAULT_Y_AXIS_COMPLEX);
			updateIterations(GUI.DEFAULT_ITERATIONS);

			gui.getPnlMandelbrot().repaint();
		}
	}

	public JLabel getLblSelectedComplexPoint()
	{
		return lblSelectedComplexPoint;
	}

	public void setLblSelectedComplexPoint(JLabel lblSelectedComplexPoint)
	{
		this.lblSelectedComplexPoint = lblSelectedComplexPoint;
	}

	class ComboBoxListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				File fileSelected = new File(GUI.IMAGE_DIRECTORY + "/"
						+ cmbJuliaFavourites.getItemAt(cmbJuliaFavourites.getSelectedIndex()));
				gui.getPnlJulia().setJuliaImage(ImageIO.read(fileSelected));
				gui.favouriteSelected = true;
				gui.getPnlJulia().repaint();
			}
			catch (IOException exc)
			{
				exc.printStackTrace();
			}

		}
	}

}