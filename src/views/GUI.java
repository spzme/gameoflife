package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import utils.CellColors;

import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private JPanel grid;
	private JTextField rowsInput;
	private JTextField columnsInput;
	private static final int ROW_BOUNDS = 150;
	private static final int COLUMN_BOUNDS = 200;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Game of Life");
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		// The grid which contains the cells
		grid = new JPanel();
		centerPanel.add(BorderLayout.CENTER, grid);
		contentPane.add(BorderLayout.CENTER, centerPanel);

		// The panel in which the user can specify things like amount of cells,
		// colors etc.
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		contentPane.add(BorderLayout.WEST, controlPanel);

		JPanel rowsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) rowsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		controlPanel.add(rowsPanel);

		JLabel rowsLabel = new JLabel("Rows");
		rowsPanel.add(rowsLabel);

		rowsInput = new JTextField();
		rowsPanel.add(rowsInput);
		rowsInput.setColumns(10);
		rowsInput.setText(String.valueOf(10));

		JPanel columnsPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) columnsPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		controlPanel.add(columnsPanel);

		JLabel columnsLabel = new JLabel("Columns");
		columnsPanel.add(columnsLabel);

		columnsInput = new JTextField();
		columnsPanel.add(columnsInput);
		columnsInput.setColumns(10);
		columnsInput.setText(String.valueOf(10));

		JPanel colorExplanationPanel = new JPanel();
		JLabel colorExplanationText = new JLabel(
				"Choose colors to represent the state of the cells");
		colorExplanationPanel.add(colorExplanationText);
		controlPanel.add(colorExplanationPanel);

		JPanel aliveColorPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) aliveColorPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		controlPanel.add(aliveColorPanel);

		JLabel aliveColorLabel = new JLabel("Alive");
		aliveColorPanel.add(aliveColorLabel);

		final JComboBox<Color> aliveColorComboBox = new JComboBox<Color>(
				CellColors.getColors());
		aliveColorComboBox.setRenderer(new ColorCellRenderer());
		aliveColorComboBox.setBackground(Color.BLACK);

		aliveColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aliveColorComboBox.setBackground((Color) aliveColorComboBox
						.getSelectedItem());
			}
		});
		aliveColorComboBox.setPreferredSize(new Dimension(100, 20));
		aliveColorPanel.add(aliveColorComboBox);

		JPanel deadColorPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) deadColorPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		controlPanel.add(deadColorPanel);

		JLabel deadColorLabel = new JLabel("Dead");
		deadColorPanel.add(deadColorLabel);

		final JComboBox<Color> deadColorComboBox = new JComboBox<Color>(
				CellColors.getColors());
		deadColorComboBox.setRenderer(new ColorCellRenderer());
		deadColorComboBox.setSelectedItem(Color.WHITE);
		deadColorComboBox.setBackground(Color.WHITE);

		deadColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deadColorComboBox.setBackground((Color) deadColorComboBox
						.getSelectedItem());
			}
		});
		deadColorComboBox.setPreferredSize(new Dimension(100, 20));
		deadColorPanel.add(deadColorComboBox);

		JPanel hasLivedColorPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) hasLivedColorPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		controlPanel.add(hasLivedColorPanel);

		JLabel hasLivedColorLabel = new JLabel("Has lived");
		hasLivedColorPanel.add(hasLivedColorLabel);

		final JComboBox<Color> hasLivedColorComboBox = new JComboBox<Color>(
				CellColors.getColors());
		hasLivedColorComboBox.setRenderer(new ColorCellRenderer());
		hasLivedColorComboBox.setSelectedItem(Color.LIGHT_GRAY);
		hasLivedColorComboBox.setBackground(Color.LIGHT_GRAY);

		hasLivedColorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasLivedColorComboBox
						.setBackground((Color) hasLivedColorComboBox
								.getSelectedItem());
			}
		});
		hasLivedColorComboBox.setPreferredSize(new Dimension(100, 20));
		hasLivedColorPanel.add(hasLivedColorComboBox);

		JButton generateButton = new JButton("Generate grid");
		controlPanel.add(generateButton);
		this.getRootPane().setDefaultButton(generateButton);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int rows = Integer.parseInt(rowsInput.getText());
					int columns = Integer.parseInt(columnsInput.getText());
					if (rows <= ROW_BOUNDS && columns <= COLUMN_BOUNDS) {
						setupCells(rows, columns,
								(Color) aliveColorComboBox.getSelectedItem(),
								(Color) deadColorComboBox.getSelectedItem(),
								(Color) hasLivedColorComboBox.getSelectedItem());
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		centerPanel.add(BorderLayout.SOUTH, buttonPanel);
		JButton previousButton = new JButton("<");
		previousButton.setEnabled(false);
		buttonPanel.add(previousButton);
		JButton startButton = new JButton("Start");
		startButton.setEnabled(false);
		buttonPanel.add(startButton);
		JButton nextButton = new JButton(">");
		nextButton.setEnabled(false);
		buttonPanel.add(nextButton);
	}

	private void setupCells(int rows, int columns, Color aliveColor,
			Color deadColor, Color hasLivedColor) {
		grid.setVisible(false);
		grid.removeAll();

		// Set the vgap and hgap to -1 to reduce waste of space around cells
		grid.setLayout(new GridLayout(rows, columns, -1, -1));
		for (int i = 0; i < rows * columns; i++) {
			Cell cell = new Cell(aliveColor, deadColor, hasLivedColor);
			cell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cell.toggleState();
				}
			});
			grid.add(cell);
		}
		grid.setVisible(true);
	}
	
	public boolean[][] getCurrentGeneration() {
		boolean[][] result = new boolean[/*ROWS*/][/*COLUMNS*/];
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				Cell c = (Cell) grid.getComponentAt(row, column);
				result[row][column] = c.isAlive();
			}
		}
		return result;
	}

}
