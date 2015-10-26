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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.Game;
import model.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import utils.CellColors;

import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private Game game;
	private Field field;
	
	private JPanel contentPane;
	private JPanel grid;
	private JTextField rowsInput;
	private JTextField columnsInput;
	private JButton generateButton;
	private JButton startButton;
	private JButton previousButton;
	private JButton nextButton;
	private JLabel cellsAlive;
	private JSlider frequencySlider;
	private static final int ROW_BOUNDS = 150;
	private static final int COLUMN_BOUNDS = 200;
	// Color variables so the user has can determine the color of the cells
	// during runtime
	private Color aliveColor;
	private Color deadColor;
	private Color hasLivedColor;

	private int rows;
	private int columns;
	/**
	 * Launch the GUI.
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
		setBounds(100, 100, 750, 300);
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
		controlPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
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
		
		JLabel sliderExplanationText = new JLabel("Generations per second");
		JPanel frequencyPanel = new JPanel();
		FlowLayout frequencyFlowLayout = (FlowLayout) frequencyPanel.getLayout();
		frequencyFlowLayout.setAlignment(FlowLayout.RIGHT);
		JLabel sliderText = new JLabel("1");
		frequencySlider = new JSlider(1, 10, 1);
		frequencySlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sliderText.setText(String.valueOf(frequencySlider.getValue()));
			}
			
		});
		frequencyPanel.add(frequencySlider);
		frequencyPanel.add(sliderText);
		controlPanel.add(sliderExplanationText);
		controlPanel.add(frequencyPanel);
		

		JLabel colorExplanationText = new JLabel("Choose colors to represent the state of the cells");
		controlPanel.add(colorExplanationText);

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

		generateButton = new JButton("Generate grid");
		controlPanel.add(generateButton);
		this.getRootPane().setDefaultButton(generateButton);
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int rows = Integer.parseInt(rowsInput.getText());
					int columns = Integer.parseInt(columnsInput.getText());
					if (rows <= ROW_BOUNDS && columns <= COLUMN_BOUNDS) {
						aliveColor = (Color) aliveColorComboBox.getSelectedItem();
						deadColor = (Color) deadColorComboBox.getSelectedItem();
						hasLivedColor = (Color) hasLivedColorComboBox.getSelectedItem();
						setupCells(rows, columns);
						getRootPane().setDefaultButton(startButton);
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		centerPanel.add(BorderLayout.SOUTH, buttonPanel);
		previousButton = new JButton("<");
		previousButton.setEnabled(false);
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				// Ash game for previous generation
			}
		});
		buttonPanel.add(previousButton);
		startButton = new JButton("Start");
		startButton.setEnabled(false);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startSimulation();
				startButton.setText("Stop");
				nextButton.setEnabled(false);
				previousButton.setEnabled(false);
				// TODO
				// determine if the game is on automatic or if stop is issued so the player can look at generations step by step
				// if stop is pressed, the next and previous button should be enabled
				//startButton.setText("Start");
				//nextButton.setEnabled(true);
				//nextButton.setEnabled(true);
			}
		});
		buttonPanel.add(startButton);
		nextButton = new JButton(">");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				// Ask game for next generation
			}
		});
		nextButton.setEnabled(false);
		
		buttonPanel.add(nextButton);
		
		cellsAlive = new JLabel();
		buttonPanel.add(cellsAlive);
		
		
		
		
		// TEMPORARY BUTTONS TO SIMULATE THE GYRO SENSOR
		JPanel gyroPanel = new JPanel();
		JLabel gyroText = new JLabel("Simulate gyro sensor");
		gyroPanel.add(gyroText);
		gyroPanel.setLayout(new BoxLayout(gyroPanel, BoxLayout.Y_AXIS));
		contentPane.add(BorderLayout.EAST, gyroPanel);
		
		JButton shakeButton = new JButton("Shake");
		gyroPanel.add(shakeButton);
		JPanel tiltPanel = new JPanel();
		JTextField tiltValue = new JTextField();
		tiltPanel.add(tiltValue);
		JLabel tiltText = new JLabel("degrees");
		tiltPanel.add(tiltText);
		tiltValue.setColumns(10);
		gyroPanel.add(tiltPanel);
	}

	private void setupCells(int rows, int columns) {
		grid.setVisible(false);
		grid.removeAll();

		this.rows = rows;
		this.columns = columns;
		
		// Set the vgap and hgap to -1 to reduce waste of space around cells
		grid.setLayout(new GridLayout(rows, columns, -1, -1));
		for (int i = 0; i < rows * columns; i++) {
			Cell cell = new Cell(this);
			cell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cell.toggleState();
				}
			});
			grid.add(cell);
		}
		grid.setVisible(true);
		startButton.setEnabled(true);
	}
	
	//tell the game to start the simulation
	public void startSimulation(){
		System.out.println("Starting simulation now!");
		
		//build a Field object
		for(int m = 0; m < this.rows; m++){
			for (int n = 0; n < this.columns; n++){
				Cell c = (Cell) grid.getComponentAt(m, n);
				field.insertCellAt(m, n, c);
			}
		}
		
		game = new Game(field);
		game.setFrequency(frequencySlider.getValue());
	}
	
	//update the GUI based on the generation.
	public void newGeneration(Field field){
		int cellsAlive = 0;
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Cell c = (Cell) grid.getComponentAt(row, column);
				boolean alive = field.getAliveState(row, column);
				if (alive) {
					cellsAlive++;
				}
				c.setAlive(alive);
			}
		}
		this.cellsAlive.setText(cellsAlive + " cells alive");
	}	
	
	public Color getAliveColor() {
		return aliveColor;
	}
	
	public Color getDeadColor() {
		return deadColor;
	}
	
	public Color getHasLivedColor() {
		return hasLivedColor;
	}
	
}
