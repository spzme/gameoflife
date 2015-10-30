package views;

import controller.Game;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import figures.Figure;
import figures.Glider;
import model.Field;
import model.Rule;
import model.Tuple;
import utils.CellColors;

@SuppressWarnings("serial")
public class NewGUI extends JFrame {
	private Thread generatingThread;
	private Game game;
	private Cell[][] cells;
	// Color variables so the user has can determine the color of the cells
	// during runtime
	private Color aliveColor;
	private Color deadColor;
	private Color hasLivedColor;

	private JComboBox<Color> aliveColorBox;
	private JComboBox<Color> deadColorBox;
	private JComboBox<Color> hasLivedColorBox;

	private JButton startButton;
	private JButton previousButton;
	private JButton nextButton;

	private JPanel contentPane;
	private JPanel grid;
	private JTextField rowInput;
	private JTextField columnInput;
	private JSlider speedSlider;
	private JLabel speedLabel;

	private JLabel lblCellsAlive;
	private JLabel lblGenerationCounter;

	private static final int ROW_BOUNDS = 100;
	private static final int COLUMN_BOUNDS = 100;

	private JTextField stayAliveField;
	private JTextField getAliveField;

	/**
	 * Launch the GUI.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				NewGUI frame = new NewGUI();
				frame.setVisible(true);
			}
		});
	}

	public NewGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Game of Life");

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		grid = new JPanel();
		contentPane.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel settingsPanel = new JPanel();
		FlowLayout fl_settingsPanel = (FlowLayout) settingsPanel.getLayout();
		fl_settingsPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(settingsPanel, BorderLayout.WEST);

		Box verticalBox = Box.createVerticalBox();
		settingsPanel.add(verticalBox);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		verticalBox.add(lblSettings);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		JLabel lblRules = new JLabel("Cell properties");
		lblRules.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.add(lblRules);

		Component verticalStrut_8 = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut_8);

		JLabel lblAmountOfNeighbours = new JLabel("Neighbours to stay alive");
		verticalBox.add(lblAmountOfNeighbours);

		stayAliveField = new JTextField();
		stayAliveField.setText("23");
		stayAliveField.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(stayAliveField);
		stayAliveField.setColumns(10);

		Component verticalStrut_7 = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut_7);

		JLabel lblAmountOfNeighbours_1 = new JLabel("Neighbours to get alive");
		verticalBox.add(lblAmountOfNeighbours_1);

		getAliveField = new JTextField();
		getAliveField.setText("3");
		getAliveField.setAlignmentX(0.0f);
		verticalBox.add(getAliveField);
		getAliveField.setColumns(10);

		Component verticalStrut_9 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_9);

		JLabel lblFieldProperties = new JLabel("Field properties");
		lblFieldProperties.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.add(lblFieldProperties);

		Component verticalStrut_10 = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut_10);

		JLabel lblRows = new JLabel("Rows");
		verticalBox.add(lblRows);

		rowInput = new JTextField();
		rowInput.setAlignmentX(Component.LEFT_ALIGNMENT);
		rowInput.setText("10");
		verticalBox.add(rowInput);
		rowInput.setColumns(10);

		Component verticalStrut_6 = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut_6);

		JLabel lblColumns = new JLabel("Columns");
		verticalBox.add(lblColumns);

		columnInput = new JTextField();
		columnInput.setAlignmentX(Component.LEFT_ALIGNMENT);
		columnInput.setText("10");
		verticalBox.add(columnInput);
		columnInput.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_1);

		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.add(lblSpeed);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox);

		speedSlider = new JSlider();
		speedSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
		horizontalBox.add(speedSlider);
		speedSlider.setMinimum(1);
		speedSlider.setMaximum(50);
		speedSlider.setValue(1);
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speedLabel.setText(String.valueOf(speedSlider.getValue()));
			}
		});

		speedLabel = new JLabel("1");
		horizontalBox.add(speedLabel);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_2);

		aliveColorBox = new JComboBox<Color>();
		aliveColorBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		for (Color c : CellColors.getColors()) {
			aliveColorBox.addItem(c);
		}
		aliveColorBox.setRenderer(new ColorCellRenderer());
		aliveColorBox.setBackground(Color.BLACK);
		aliveColorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aliveColorBox.setBackground((Color) aliveColorBox
						.getSelectedItem());
			}
		});

		JLabel cellsAliveLabel = new JLabel("Color when cell is alive");
		cellsAliveLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.add(cellsAliveLabel);
		verticalBox.add(aliveColorBox);

		deadColorBox = new JComboBox<Color>();
		deadColorBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		for (Color c : CellColors.getColors()) {
			deadColorBox.addItem(c);
		}
		deadColorBox.setRenderer(new ColorCellRenderer());
		deadColorBox.setSelectedItem(Color.WHITE);
		deadColorBox.setBackground(Color.WHITE);
		deadColorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deadColorBox.setBackground((Color) deadColorBox
						.getSelectedItem());
			}
		});

		Component verticalStrut_4 = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut_4);

		JLabel lblDead = new JLabel("Color when cell is dead");
		lblDead.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.add(lblDead);
		verticalBox.add(deadColorBox);

		hasLivedColorBox = new JComboBox<Color>();
		hasLivedColorBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		for (Color c : CellColors.getColors()) {
			hasLivedColorBox.addItem(c);
		}
		hasLivedColorBox.setRenderer(new ColorCellRenderer());
		hasLivedColorBox.setSelectedItem(Color.LIGHT_GRAY);
		hasLivedColorBox.setBackground(Color.LIGHT_GRAY);
		hasLivedColorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasLivedColorBox.setBackground((Color) hasLivedColorBox
						.getSelectedItem());
			}
		});

		Component verticalStrut_5 = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut_5);

		JLabel lblHasLived = new JLabel("Color when cell has lived");
		lblHasLived.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.add(lblHasLived);
		verticalBox.add(hasLivedColorBox);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		JButton btnGenerateGrid = new JButton("Generate grid");
		btnGenerateGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generateField();
			}
		});
		getRootPane().setDefaultButton(btnGenerateGrid);
		verticalBox.add(btnGenerateGrid);
		JPanel buttonPanel = new JPanel();
		previousButton = new JButton("<");
		previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousGeneration();
			}
		});
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (generatingThread == null) {
					startGame();
				} else {
					stopGame();
				}
			}
		});
		nextButton = new JButton(">");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextGeneration();
			}
		});

		lblGenerationCounter = new JLabel("Generation 0");
		buttonPanel.add(lblGenerationCounter);
		buttonPanel.add(previousButton);
		buttonPanel.add(startButton);
		buttonPanel.add(nextButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		lblCellsAlive = new JLabel();
		displayCellsAlive(0);
		buttonPanel.add(lblCellsAlive);

		JPanel extraPanel = new JPanel();
		contentPane.add(extraPanel, BorderLayout.EAST);

		Box verticalBox_1 = Box.createVerticalBox();
		extraPanel.add(verticalBox_1);

		JLabel figureLabel = new JLabel("Figures");
		figureLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox_1.add(figureLabel);
		
		Component verticalStrut_11 = Box.createVerticalStrut(5);
		verticalBox_1.add(verticalStrut_11);

		JComboBox figureComboBox = new JComboBox();
		figureComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		figureComboBox.addItem(new Glider());
		verticalBox_1.add(figureComboBox);

		JButton createFigureButton = new JButton("Create");
		createFigureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generateField();
				Figure f = (Figure) figureComboBox.getSelectedItem();
				boolean[][] field = f.getField(game.getField().getColumnCount(), game.getField().getRowCount());
				for (int x = 0; x < game.getField().getColumnCount(); x++) {
					for (int y = 0; y < game.getField().getRowCount(); y++) {
						cells[x][y].setAlive(field[x][y]);
						game.getField().setAlive(x, y, cells[x][y].isAlive());
					}
				}
			}
		});
		
		Component verticalStrut_12 = Box.createVerticalStrut(5);
		verticalBox_1.add(verticalStrut_12);
		verticalBox_1.add(createFigureButton);

		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void generateField() {
		stopGame();
		try {
			int rows = Integer.parseInt(rowInput.getText());
			int columns = Integer.parseInt(columnInput.getText());
			if (rows <= ROW_BOUNDS && columns <= COLUMN_BOUNDS) {
				aliveColor = (Color) aliveColorBox.getSelectedItem();
				deadColor = (Color) deadColorBox.getSelectedItem();
				hasLivedColor = (Color) hasLivedColorBox.getSelectedItem();

				grid.setVisible(false);
				grid.removeAll();

				game = new Game(new Field(rows, columns), new Rule(
						stayAliveField.getText(), getAliveField.getText()));
				cells = new Cell[rows][columns];
				// Set the vgap and hgap to -1 to reduce waste of space around
				// cells
				grid.setLayout(new GridLayout(rows, columns, -1, -1));
				for (int row = 0; row < rows; row++) {
					for (int column = 0; column < columns; column++) {
						int xx = row;
						int yy = column;
						cells[xx][yy] = new Cell(this);
						cells[xx][yy].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								cells[xx][yy].toggleState();
								game.getField().setAlive(xx, yy,
										cells[xx][yy].isAlive());
							}
						});
						grid.add(cells[xx][yy]);
					}
				}
				grid.setVisible(true);
				this.pack();
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

		startButton.setEnabled(true);
	}

	private void nextGeneration() {
		displayGenerationCount();
		updateGrid(game.nextGeneration());
	}

	private void previousGeneration() {
		displayGenerationCount();
		updateGrid(game.previousGeneration());
	}

	private void startGame() {
		startButton.setText("Stop");
		int speed = speedSlider.getValue();
		long timeToWait = 1000 / speed;
		generatingThread = new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(timeToWait);
						nextGeneration();
					}
				} catch (InterruptedException e) {
					// Do nothing
				}
			}
		};
		generatingThread.start();
	}

	public void stopGame() {
		startButton.setText("Start");
		displayCellsAlive(0);
		if (generatingThread != null) {
			displayGenerationCount();
			generatingThread.interrupt();
			generatingThread = null;
		}
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

	// update the GUI based on the generation.
	private void updateGrid(Field field) {
		int cellsAlive = field.getAliveCellCount();
		for (Tuple t : game.getDifferences()) {
			cells[t.x][t.y].setAlive(field.getAliveState(t.x, t.y));
		}
		displayCellsAlive(cellsAlive);
	}
	
	public void updateGridTotally(Field field) {
		int cellsAlive = field.getAliveCellCount();
		for (int x = 0; x < field.getColumnCount(); x++) {
			for (int y = 0; y < field.getRowCount(); y++) {
				cells[x][y].setAlive(field.getAliveState(x, y));
			}
		}
		displayCellsAlive(cellsAlive);
	}

	private void displayCellsAlive(int cellsAlive) {
		lblCellsAlive.setText(String.valueOf(cellsAlive) + " cells alive");
	}

	private void displayGenerationCount() {
		lblGenerationCounter.setText("Generation " + game.getGenerationCount());
	}
}
