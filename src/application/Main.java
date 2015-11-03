package application;

import java.io.IOException;

import model.Field;
import model.Rule;
import model.Tuple;
import sound.SoundController;
import views.Cell;
import controller.Game;
import figures.Blinker;
import figures.Figure;
import figures.Glider;
import figures.GliderGun;
import figures.None;
import figures.Pulsar;
import figures.Toad;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	private Thread generatingThread;
	private Thread gyroRuleThread;
	private Thread gyroInputThread;

	private Game game;
	private Cell[][] cells;

	private ColorPicker aliveColorPicker;
	private ColorPicker deadColorPicker;
	private ColorPicker hasLivedColorPicker;

	private Color aliveColor;
	private Color deadColor;
	private Color hasLivedColor;

	private GridPane cellGrid;

	private Button startButton;
	private Button previousButton;
	private Button nextButton;

	private Button generateButton;

	private TextField rowInputField;
	private TextField columnInputField;
	private Slider speedSlider;

	private Label cellsAlive;
	private Label generationCounter;

	private ComboBox<Figure> figurePicker;

	private static final int ROW_BOUNDS = 50;
	private static final int COLUMN_BOUNDS = 50;

	// Check for movements 4 times per second.
	private static final int GYRO_RULE_FREQ = 4;
	// Read gyro inputs 40 times per second.
	private static final int GYRO_INPUT_FREQ = 40;

	private CheckBox[] stayAliveBoxes;
	private CheckBox[] getAliveBoxes;

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
			primaryStage.show();
			Label speedLabel = (Label) scene.lookup("#speedValue");
			speedSlider = (Slider) scene.lookup("#speedSlider");
			speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
					speedLabel.setText(String.valueOf(new_val.intValue()));
				}
			});
			aliveColorPicker = (ColorPicker) scene.lookup("#aliveColorPicker");
			aliveColorPicker.setValue(Color.BLACK);
			deadColorPicker = (ColorPicker) scene.lookup("#deadColorPicker");
			hasLivedColorPicker = (ColorPicker) scene.lookup("#hasLivedColorPicker");
			hasLivedColorPicker.setValue(Color.GREY);
			cellGrid = (GridPane) scene.lookup("#cellGrid");
			figurePicker = (ComboBox<Figure>) scene.lookup("#figurePicker");
			figurePicker.setItems(FXCollections.observableArrayList(new None(), new Blinker(), new Toad(),
					new Pulsar(), new Glider(), new GliderGun()));
			figurePicker.getSelectionModel().select(0);
			startButton = (Button) scene.lookup("#startButton");
			startButton.setDisable(true);
			startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (generatingThread == null) {
						startGame();
					} else {
						stopGame();
					}
				}
			});
			nextButton = (Button) scene.lookup("#nextButton");
			nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					nextGeneration();
				}
			});
			previousButton = (Button) scene.lookup("#previousButton");
			previousButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					previousGeneration();
				}
			});
			generateButton = (Button) scene.lookup("#generateButton");
			generateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					try {
						Figure f = figurePicker.getSelectionModel().getSelectedItem();
						int rows = Integer.parseInt(rowInputField.getText());
						int columns = Integer.parseInt(columnInputField.getText());
						if (rows < f.rows) {
							rowInputField.setText(String.valueOf(f.rows));
							rows = f.rows;
						}
						if (columns < f.columns) {
							columnInputField.setText(String.valueOf(f.columns));
							columns = f.columns;
						}
						generateField();
						boolean[][] field = f.getField(columns, rows);
						for (int x = 0; x < game.getField().getColumnCount(); x++) {
							for (int y = 0; y < game.getField().getRowCount(); y++) {
								cells[x][y].setAlive(field[x][y]);
								game.getField().setAlive(x, y, cells[x][y].isAlive());
							}
						}
					} catch (NumberFormatException nfe) {
						// Do nothing
					}
				}
			});

			cellsAlive = (Label) scene.lookup("#cellsAliveLabel");
			generationCounter = (Label) scene.lookup("#generationCounterLabel");
			stayAliveBoxes = new CheckBox[9];
			stayAliveBoxes[0] = (CheckBox) scene.lookup("#stayAliveBox0");
			stayAliveBoxes[1] = (CheckBox) scene.lookup("#stayAliveBox1");
			stayAliveBoxes[2] = (CheckBox) scene.lookup("#stayAliveBox2");
			stayAliveBoxes[3] = (CheckBox) scene.lookup("#stayAliveBox3");
			stayAliveBoxes[4] = (CheckBox) scene.lookup("#stayAliveBox4");
			stayAliveBoxes[5] = (CheckBox) scene.lookup("#stayAliveBox5");
			stayAliveBoxes[6] = (CheckBox) scene.lookup("#stayAliveBox6");
			stayAliveBoxes[7] = (CheckBox) scene.lookup("#stayAliveBox7");
			stayAliveBoxes[8] = (CheckBox) scene.lookup("#stayAliveBox8");
			getAliveBoxes = new CheckBox[9];
			getAliveBoxes[0] = (CheckBox) scene.lookup("#getAliveBox0");
			getAliveBoxes[1] = (CheckBox) scene.lookup("#getAliveBox1");
			getAliveBoxes[2] = (CheckBox) scene.lookup("#getAliveBox2");
			getAliveBoxes[3] = (CheckBox) scene.lookup("#getAliveBox3");
			getAliveBoxes[4] = (CheckBox) scene.lookup("#getAliveBox4");
			getAliveBoxes[5] = (CheckBox) scene.lookup("#getAliveBox5");
			getAliveBoxes[6] = (CheckBox) scene.lookup("#getAliveBox6");
			getAliveBoxes[7] = (CheckBox) scene.lookup("#getAliveBox7");
			getAliveBoxes[8] = (CheckBox) scene.lookup("#getAliveBox8");
			rowInputField = (TextField) scene.lookup("#rowInputField");
			columnInputField = (TextField) scene.lookup("#columnInputField");

			primaryStage.sizeToScene();

			playMusic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void generateField() {
		stopGame();
		try {
			int rows = Integer.parseInt(rowInputField.getText());
			int columns = Integer.parseInt(columnInputField.getText());
			if (rows <= ROW_BOUNDS && columns <= COLUMN_BOUNDS) {

				aliveColor = (Color) aliveColorPicker.getValue();
				deadColor = (Color) deadColorPicker.getValue();
				hasLivedColor = (Color) hasLivedColorPicker.getValue();

				boolean[] survive = { stayAliveBoxes[0].isSelected(), stayAliveBoxes[1].isSelected(),
						stayAliveBoxes[2].isSelected(), stayAliveBoxes[3].isSelected(), stayAliveBoxes[4].isSelected(),
						stayAliveBoxes[5].isSelected(), stayAliveBoxes[6].isSelected(), stayAliveBoxes[7].isSelected(),
						stayAliveBoxes[8].isSelected() };
				boolean[] birth = { getAliveBoxes[0].isSelected(), getAliveBoxes[1].isSelected(),
						getAliveBoxes[2].isSelected(), getAliveBoxes[3].isSelected(), getAliveBoxes[4].isSelected(),
						getAliveBoxes[5].isSelected(), getAliveBoxes[6].isSelected(), getAliveBoxes[7].isSelected(),
						getAliveBoxes[8].isSelected() };
				game = new Game(new Field(columns, rows), new Rule(survive, birth));

				cells = new Cell[columns][rows];

				cellGrid.getChildren().clear();
				for (int row = 0; row < rows; row++) {
					for (int column = 0; column < columns; column++) {
						int xx = column;
						int yy = row;
						cells[xx][yy] = new Cell(this);
						int max = Math.max(columns, rows);
						cells[xx][yy].setPrefWidth(800 / max);
						cells[xx][yy].setPrefHeight(800 / max);
						cells[xx][yy].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								cells[xx][yy].toggleState();
								game.getField().setAlive(xx, yy, cells[xx][yy].isAlive());
							}
						});
						cellGrid.add(cells[xx][yy], column, row);
					}
				}
				primaryStage.sizeToScene();
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

		startButton.setDisable(false);
	}

	private void displayCellsAlive(int cellsAliveInt) {
		Platform.runLater(new Runnable() {
			public void run() {
				cellsAlive.setText(String.valueOf(cellsAliveInt) + " cells alive");
			}
		});
	}

	public void stopGame() {
		startButton.setText("Start");
		displayCellsAlive(0);
		if (generatingThread != null) {
			displayGenerationCount();
			generatingThread.interrupt();
			generatingThread = null;
		}
		if (gyroInputThread != null) {
			gyroInputThread.interrupt();
			generatingThread = null;
			game.clearGyroInputHistory();
		}
		if (gyroRuleThread != null) {
			gyroRuleThread.interrupt();
			gyroRuleThread = null;
		}
	}

	private void startGame() {
		startButton.setText("Stop");
		int speed = (int) speedSlider.getValue();
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

		long timeToWaitGyroRule = 1000 / GYRO_RULE_FREQ;
		gyroRuleThread = new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(timeToWaitGyroRule);
						checkGyroRule();
					}
				} catch (InterruptedException e) {
					// Do nothing
				}
			}
		};
		long timeToWaitGyroInput = 1000 / GYRO_INPUT_FREQ;
		gyroInputThread = new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(timeToWaitGyroInput);
						game.receiveInputFromGyro();
					}
				} catch (InterruptedException e) {
					// Do nothing
				}
			}
		};
		generatingThread.start();
		gyroInputThread.start();
		gyroRuleThread.start();
	}

	private void nextGeneration() {
		displayGenerationCount();
		updateGrid(game.nextGeneration());
	}

	private void previousGeneration() {
		displayGenerationCount();
		updateGrid(game.previousGeneration());
	}

	public void checkGyroRule() {
		updateGrid(game.applyGyroRule());
	}

	// update the GUI based on the generation.
	private void updateGrid(Field field) {
		int cellsAlive = field.getAliveCellCount();
		for (Tuple t : game.getDifferences()) {
			cells[t.x][t.y].setAlive(field.getAliveState(t.x, t.y));
		}
		displayCellsAlive(cellsAlive);
	}

	private void displayGenerationCount() {
		Platform.runLater(new Runnable() {
			public void run() {
				generationCounter.setText("Generation " + game.getGenerationCount());
			}
		});
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

	private void playMusic() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					SoundController.playAudio(true);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
