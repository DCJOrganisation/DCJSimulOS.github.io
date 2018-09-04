package vue;
import java.io.File;
import java.security.acl.Group;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import Software.*;
import application.Main;
import vue.CopyTask;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;
;

public class Display implements Runnable {
	public static CopyTask copyTask;
	public static MenuBar menu() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		MenuItem start = new MenuItem("Start");
		MenuItem cancel = new MenuItem("Cancel");
		//start.setOnAction();
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e->{System.exit(0);});
		MenuItem about = new MenuItem("About DCJSimulOS");
		about.setOnAction(e->{Main.println("en cours...");});
		menuBar.getMenus().add(file);
		menuBar.getMenus().add(help);
		file.getItems().add(start);
		file.getItems().add(cancel);
		file.getItems().add(exit);
		help.getItems().add(about);
		return menuBar;
	}

	public static PieChart pie_chart_init() {
		final PieChart chart = new PieChart(); 
		chart.setPrefSize(100, 100);
		chart.setLegendSide(Side.LEFT);
		chart.setLabelLineLength(5);
		chart.getData().setAll(new PieChart.Data("", 100)
				//, new PieChart.Data("P1.exe", 30),  
//				new PieChart.Data("P2.exe", 25), new PieChart.Data("P3.exe", 42), 
//				new PieChart.Data("P4.exe", 5) ,new PieChart.Data("P5.exe", 10)
				); 
		return chart;	
	}
	
	public static PieChart pie_chart_info() {
		final PieChart chart = new PieChart(); 
		chart.setPrefSize(100, 100);
		chart.setLegendSide(Side.LEFT);
		chart.setLabelLineLength(5);
		chart.getData().setAll(new PieChart.Data("", 20)
				,new PieChart.Data("O/S", 40),  
				new PieChart.Data("P2.exe", 7), new PieChart.Data("P3.exe", 6), 
				new PieChart.Data("P4.exe", 8) ,new PieChart.Data("P5.exe", 10),
				new PieChart.Data("P1.exe", 9)
				); 
		return chart;	
	}
	
	public static PieChart pie_chart_OS() {
		final PieChart chart = new PieChart(); 
		chart.setPrefSize(100, 100);
		chart.setLegendSide(Side.LEFT);
		chart.setLabelLineLength(5);
		chart.getData().setAll(new PieChart.Data("", 60)
				,new PieChart.Data("O/S", 40) 
				); 
		return chart;	
	}

	////////////////////////////////////////////////////////////////////////////////
	public static ObservableList<Processus> getProcessus(){
		ObservableList<Processus> processus = FXCollections.observableArrayList();
		processus.add(new Processus(1, "P1.exe"));
		processus.add(new Processus(7, "P2.exe"));
		processus.add(new Processus(9, "P3.exe"));
		processus.add(new Processus(0, "P4.exe"));
		processus.add(new Processus(4, "P5.exe"));
		return processus;
	}


	public static TableView<Processus> tabview(){
		TableColumn<Processus, String> nameColumn =new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_process"));
		TableColumn<Processus, String> pidColumn =new TableColumn<>("Pid");
		pidColumn.setMinWidth(100);
		pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid_process"));
		TableView<Processus> table = new TableView<>();
		table.getColumns().add(pidColumn);
		table.getColumns().add(nameColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//table.setItems(getProcessus());
		return table;


	}
	
	public static TableView<Processus> tabviewInfo(){
		TableColumn<Processus, String> nameColumn =new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_process"));
		TableColumn<Processus, String> pidColumn =new TableColumn<>("Pid");
		pidColumn.setMinWidth(100);
		pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid_process"));
		TableView<Processus> table = new TableView<>();
		table.setItems(getProcessus());
		table.getColumns().add(pidColumn);
		table.getColumns().add(nameColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		return table;


	}

	//	public static Tab info_process(){
	//		final ProgressBar progressBar = new ProgressBar(0);
	//		final ProgressIndicator progressIndicator = new ProgressIndicator(-1);
	//		// Create a Task.
	//		copyTask = new CopyTask();
	//
	//		// Unbind progress property
	//		progressBar.progressProperty().unbind();
	//
	//		// Bind progress property
	//		progressBar.progressProperty().bind(copyTask.progressProperty());
	//
	//		// Hủy bỏ kết nối thuộc tính progress
	//		progressIndicator.progressProperty().unbind();
	//
	//		// Bind progress property.
	//		progressIndicator.progressProperty().bind(copyTask.progressProperty());
	//		// When completed tasks
	//		copyTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
	//				new EventHandler<WorkerStateEvent>() {
	//
	//			@Override
	//			public void handle(WorkerStateEvent t) {
	//				List<File> copied = copyTask.getValue();
	//				System.out.println("papa");
	//				TabPane tabpane = new TabPane();
	//				//tabpane.setSide(Side.LEFT);
	//				tabpane.setNodeOrientation(NodeOrientation.INHERIT);
	//				Tab tab_disque = new Tab("Disk");
	//				tab_disque.setClosable(false);
	//				tabpane.getTabs().setAll(Display.OverView_Init(), Display.tabmemory(), tab_disque);
	//				BorderPane.setAlignment(tabpane, Pos.TOP_RIGHT);
	//				BorderPane.setMargin(tabpane, new Insets(5, 5, 3, 5));
	//				//ajout de notre tabpane a droite de la scene
	//			}
	//		});
	//
	//		// Start the Task.
	//		new Thread(copyTask).start();
	//
	//		Tab overview = new Tab("OverView");
	//		///////////////////////////////////////////////////////////////////////////////
	//		VBox label_io_ready_queue = new VBox();
	//		label_io_ready_queue.setSpacing(10);
	//		label_io_ready_queue.setAlignment(Pos.CENTER);
	//		Label label1 = new Label("IO QUEUE");
	//		label1.setTextFill(Color.BLUE);
	//		label1.setFont(Font.font("", 15));
	//		Label label_ready_queue = new Label("READY QUEUE");
	//		label_ready_queue.setTextFill(Color.BLUE);
	//		label_ready_queue.setFont(Font.font("", 15));
	//		VBox global_vbox = new VBox();
	//		HBox hbox = new HBox();
	//		VBox label_vbox = new VBox();
	//		VBox label_progress = new VBox();
	//		label_progress.setSpacing(10);
	//		label_progress.setAlignment(Pos.CENTER);
	//		Label label11 = new Label("DCJsimulOS");
	//		label11.setTextFill(Color.BLUE);
	//		label11.setFont(Font.font("Cambria", 32));
	//		label_vbox.setPadding(new Insets(3, 5, 0, 5));
	//		label_vbox.setSpacing(5);
	//		TextArea label_bottom = new TextArea();
	//		label_bottom.setText("loading.............................\n..........................\n..........................\n............................\n.............................\n......................................\n..............................................................\n.......................................................................................................\n................................................................\n............................................................");
	//		label_bottom.setPadding(new Insets(2, 2, 2, 2));
	//		VBox.setMargin(label_bottom, new Insets(0, 0, 5, 0));
	//		TableView<Processus> table = tabview();
	//		hbox.setAlignment(Pos.CENTER);
	//		HBox.setMargin(table, new Insets(0, 0, 0, 150));
	//		HBox.setMargin(label_progress , new Insets(90, 0, 0, 170));
	//		label_progress.getChildren().addAll(label11, progressBar, progressIndicator);
	//		hbox.getChildren().addAll(label_progress, table);
	//		hbox.setPadding(new Insets(7, 5, 5, 5));
	//		hbox.setSpacing(5);
	//		label_vbox.getChildren().add(label_bottom);
	//		global_vbox.getChildren().addAll(hbox, label_vbox);
	//		overview.setContent(global_vbox);
	//		overview.setClosable(false);
	//		return overview;			
	//
	//	}

	public static Tab OverView_Init(){
		Tab overview = new Tab("OverView");

		final Path path = new Path();
		path.getElements().add(new MoveTo(90,90));
		path.getElements().add(new CubicCurveTo(50, 100, 800, 100, 100, 200));
		path.setOpacity(0.0);
		BorderPane root = new BorderPane();

		root.getChildren().add(path);
		final Reflection reflection = new Reflection();
		reflection.setFraction(1.0);
		final Shape shape = TextBuilder.create()
				.text("DCJSimulos").x(20).y(20)
				.font(Font.font(java.awt.Font.SANS_SERIF, 25))
				.effect(reflection)
				.build();      

		root.getChildren().add(shape);


		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(8.0));
		pathTransition.setDelay(Duration.seconds(.5));
		pathTransition.setPath(path);
		pathTransition.setNode(shape);
		pathTransition.setOrientation(OrientationType.NONE);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(true);



		final ParallelTransition parallelTransition =
				new ParallelTransition(pathTransition);
		parallelTransition.play(); 
		/////////////////////////////////////////////////////////////////////////////
	
		VBox global_vbox = new VBox();
		HBox hbox = new HBox();
		VBox label_vbox = new VBox();
		label_vbox.setPadding(new Insets(5, 5, 5, 5));
		label_vbox.setSpacing(5);
		TableView<Processus> table = tabview();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(table, new Insets(0, 0, 0, 0));
		hbox.getChildren().addAll(table);
		hbox.setPadding(new Insets(7, 5, 5, 5));
		hbox.setSpacing(5);
        ScrollPane s1 = new ScrollPane();
        s1.setVvalue(1.0);
        s1.setPrefSize(200, 200);
		s1.setContent(text());
		s1.setPadding(new Insets(15, 15, 1, 15));
		label_vbox.getChildren().add(s1);
		global_vbox.getChildren().addAll(root, hbox, label_vbox );
		overview.setContent(global_vbox);
		overview.setClosable(false);
		return overview;

	}

	public static Tab info_Process(){
		Tab overview = new Tab("OverView");
		///////////////////////////////////////////////////////////////////////////////
		VBox global_vbox = new VBox();
		HBox hbox = new HBox();
		VBox label_vbox = new VBox();
		VBox label_io_ready_queue = new VBox();
		label_io_ready_queue.setSpacing(10);
		label_io_ready_queue.setAlignment(Pos.CENTER);
		Label label = new Label("IO QUEUE");
		label.setTextFill(Color.BLUE);
		label.setFont(Font.font("", 15));
		Label label_ready_queue = new Label("READY QUEUE");
		label_ready_queue.setTextFill(Color.BLUE);
		label_ready_queue.setFont(Font.font("", 15));
		label_vbox.setPadding(new Insets(3, 5, 0, 5));
		label_vbox.setSpacing(5);
		//////////////////////////////////////////////////////////////////////////////////////////////////
	
//		ScrollPane scrollPane = new ScrollPane();
//		scrollPane.setContent(textarea());
//		scrollPane.setFitToWidth(true);
//		scrollPane.setPrefWidth(400);
//		scrollPane.setPrefHeight(180);
//
//		VBox vBox = VBoxBuilder.create()
//				.children(scrollPane)
//				.build();
		///////////////////////////////////////////////////////////////////////////////////////////////////
		ScrollPane s1 = new ScrollPane();
		s1.setVvalue(1.0);
        s1.setPrefSize(200, 200);
		s1.setContent(Display.text2());
		s1.setPadding(new Insets(15, 15, 15, 15));
		VBox.setMargin(s1, new Insets(1,1,1,1));
		label_vbox.getChildren().add(s1);
		TableView<Processus> table = tabviewInfo();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(table, new Insets(0, 0, 0, 50));
		HBox.setMargin(label_io_ready_queue, new Insets(60, 0, 60, 5));
		label_io_ready_queue.getChildren().addAll(label, Io_Queue(),label_ready_queue, Ready_Queue());
		hbox.getChildren().addAll(label_io_ready_queue, table);
		hbox.setPadding(new Insets(7, 5, 5, 5));
		hbox.setSpacing(5);
		global_vbox.getChildren().addAll(hbox, label_vbox);
		overview.setContent(global_vbox);
		overview.setClosable(false);
		return overview;

	}


	public static Tab tabmemoryInit() {
		final Tab tab_memoireRam = new Tab("Memory");
		tab_memoireRam.setClosable(false);
		//on cree un piechart memoire q'on ajoute a la tab memoire
		tab_memoireRam.setContent(pie_chart_init());
		return tab_memoireRam;
	}

	public static Tab tabmemoryInfo() {
		final Tab tab_memoireRam = new Tab("Memory");
		tab_memoireRam.setClosable(false);
		//on cree un piechart memoire q'on ajoute a la tab memoire
		tab_memoireRam.setContent(pie_chart_info());
		return tab_memoireRam;
	}
	public static Tab tabmemoryOS() {
		final Tab tab_memoireRam = new Tab("Memory");
		tab_memoireRam.setClosable(false);
		//on cree un piechart memoire q'on ajoute a la tab memoire
		tab_memoireRam.setContent(pie_chart_OS());
		return tab_memoireRam;
	}
	
	
	public static Tab messages() {
		final Tab tab_messages = new Tab("Messages");
		tab_messages.setClosable(false);
		//on cree un piechart memoire q'on ajoute a la tab memoire
		tab_messages.setContent(text());
		return tab_messages;
	}

	public static ListView<Processus> Io_Queue() {
		ListView<Processus> io_queue = new ListView<Processus>();
		io_queue.setFocusTraversable(false);
		io_queue.setMaxHeight(50);
		io_queue.setMinWidth(400);
		io_queue.setDisable(false);
		io_queue.setOrientation(Orientation.HORIZONTAL);
		io_queue.setOrientation(Orientation.HORIZONTAL);
		io_queue.getItems().addAll(new Processus(1,"P1.exe"));
		io_queue.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return io_queue;
	}

	public static ListView<Processus> Ready_Queue() {
		ListView<Processus> ready_queue = new ListView<Processus>();
		ready_queue.setFocusTraversable(false);
		ready_queue.setMaxHeight(50);
		ready_queue.setMinWidth(400);
		ready_queue.setDisable(false);
		ready_queue.setOrientation(Orientation.HORIZONTAL);
		ready_queue.getItems().addAll(new Processus(1,"P1.exe"));
		ready_queue.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return ready_queue;
	}


	public static Text text() {
		final String content = "WELCOME TO DCJSimulOS Go to the menu bar file click on start to launch the simulation";
		final Text text = new Text(10, 20, "");

		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(10000));
			}

			protected void interpolate(double frac) {
				final int length = content.length();
				final int n = Math.round(length * (float) frac);
				text.setText(content.substring(0, n));
			}

		};

		animation.play();
		return text;
	}
	
	public static Text text_test() {
		final String content = "DISK -------------------------OK\nCPU -------------------------OK\nLOADING SYSTEM..................................";
		final Text text = new Text(10, 20, "");

		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(12000));
			}

			protected void interpolate(double frac) {
				final int length = content.length();
				final int n = Math.round(length * (float) frac);
				text.setText(content.substring(0, n));
			}

		};

		animation.play();
		return text;
	}
	
	
	public static Text text2() {
		final String content = "Creation du processus 0 de taille 10 et de niveau priorite 1 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Espace suffisant\r\n" + 
				"processus de PID generer0\r\n" + 
				"Etat de la moire. Les cases ayant pour valuer -1 sont vides\r\n" + 
				"Case[0]=0\r\n" + 
				"Case[1]=0\r\n" + 
				"Case[2]=0\r\n" + 
				"Case[3]=0\r\n" + 
				"Case[4]=0\r\n" + 
				"Case[5]=0\r\n" + 
				"Case[6]=0\r\n" + 
				"Case[7]=0\r\n" + 
				"Case[8]=0\r\n" + 
				"Case[9]=0\r\n" + 
				"Case[10]=-1\r\n" + 
				"Case[11]=-1\r\n" + 
				"Case[12]=-1\r\n" + 
				"Case[13]=-1\r\n" + 
				"Case[14]=-1\r\n" + 
				"Case[15]=-1\r\n" + 
				"Case[16]=-1\r\n" + 
				"Case[17]=-1\r\n" + 
				"Case[18]=-1\r\n" + 
				"Case[19]=-1\r\n" + 
				"Case[20]=-1\r\n" + 
				"Case[21]=-1\r\n" + 
				"Case[22]=-1\r\n" + 
				"Case[23]=-1\r\n" + 
				"Case[24]=-1\r\n" + 
				"Case[25]=-1\r\n" + 
				"Case[26]=-1\r\n" + 
				"Case[27]=-1\r\n" + 
				"Case[28]=-1\r\n" + 
				"Case[29]=-1\r\n" + 
				"Case[30]=-1\r\n" + 
				"Case[31]=-1\r\n" + 
				"Case[32]=-1\r\n" + 
				"Case[33]=-1\r\n" + 
				"Case[34]=-1\r\n" + 
				"Case[35]=-1\r\n" + 
				"Case[36]=-1\r\n" + 
				"Case[37]=-1\r\n" + 
				"Case[38]=-1\r\n" + 
				"Case[39]=-1\r\n" + 
				"Case[40]=-1\r\n" + 
				"Case[41]=-1\r\n" + 
				"Case[42]=-1\r\n" + 
				"Case[43]=-1\r\n" + 
				"Case[44]=-1\r\n" + 
				"Case[45]=-1\r\n" + 
				"Case[46]=-1\r\n" + 
				"Case[47]=-1\r\n" + 
				"Case[48]=-1\r\n" + 
				"Case[49]=-1\r\n" + 
				"Case[50]=-1\r\n" + 
				"Case[51]=-1\r\n" + 
				"Case[52]=-1\r\n" + 
				"Case[53]=-1\r\n" + 
				"Case[54]=-1\r\n" + 
				"Case[55]=-1\r\n" + 
				"Case[56]=-1\r\n" + 
				"Case[57]=-1\r\n" + 
				"Case[58]=-1\r\n" + 
				"Case[59]=-1\r\n" + 
				"Case[60]=-1\r\n" + 
				"Case[61]=-1\r\n" + 
				"Case[62]=-1\r\n" + 
				"Case[63]=-1\r\n" + 
				"Case[64]=-1\r\n" + 
				"Case[65]=-1\r\n" + 
				"Case[66]=-1\r\n" + 
				"Case[67]=-1\r\n" + 
				"Case[68]=-1\r\n" + 
				"Case[69]=-1\r\n" + 
				"Case[70]=-1\r\n" + 
				"Case[71]=-1\r\n" + 
				"Case[72]=-1\r\n" + 
				"Case[73]=-1\r\n" + 
				"Case[74]=-1\r\n" + 
				"Case[75]=-1\r\n" + 
				"Case[76]=-1\r\n" + 
				"Case[77]=-1\r\n" + 
				"Case[78]=-1\r\n" + 
				"Case[79]=-1\r\n" + 
				"Case[80]=-1\r\n" + 
				"Case[81]=-1\r\n" + 
				"Case[82]=-1\r\n" + 
				"Case[83]=-1\r\n" + 
				"Case[84]=-1\r\n" + 
				"Case[85]=-1\r\n" + 
				"Case[86]=-1\r\n" + 
				"Case[87]=-1\r\n" + 
				"Case[88]=-1\r\n" + 
				"Case[89]=-1\r\n" + 
				"Case[90]=-1\r\n" + 
				"Case[91]=-1\r\n" + 
				"Case[92]=-1\r\n" + 
				"Case[93]=-1\r\n" + 
				"Case[94]=-1\r\n" + 
				"Case[95]=-1\r\n" + 
				"Case[96]=-1\r\n" + 
				"Case[97]=-1\r\n" + 
				"Case[98]=-1\r\n" + 
				"Case[99]=-1\r\n" + 
				"0\r\n" + 
				"en cours...\r\n" + 
				"Utilisation du clavier\r\n" + 
				"Le process de pid 0occupe la ressource xxxxxx pour une duree de 5 secondes\r\n" + 
				"Creation du processus 1 de taille 7 et de niveau priorite 3 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Espace suffisant\r\n" + 
				"processus de PID generer1\r\n" + 
				"Etat de la moire. Les cases ayant pour valuer -1 sont vides\r\n" + 
				"Case[0]=0\r\n" + 
				"Case[1]=0\r\n" + 
				"Case[2]=0\r\n" + 
				"1\r\n" + 
				"Case[3]=0\r\n" + 
				"Case[4]=0\r\n" + 
				"Case[5]=0\r\n" + 
				"Case[6]=0\r\n" + 
				"Case[7]=0\r\n" + 
				"Case[8]=0\r\n" + 
				"Case[9]=0\r\n" + 
				"Case[10]=1\r\n" + 
				"Case[11]=1\r\n" + 
				"Case[12]=1\r\n" + 
				"Case[13]=1\r\n" + 
				"Case[14]=1\r\n" + 
				"Case[15]=1\r\n" + 
				"Case[16]=1\r\n" + 
				"Case[17]=-1\r\n" + 
				"Case[18]=-1\r\n" + 
				"Case[19]=-1\r\n" + 
				"Case[20]=-1\r\n" + 
				"Case[21]=-1\r\n" + 
				"Case[22]=-1\r\n" + 
				"Case[23]=-1\r\n" + 
				"Case[24]=-1\r\n" + 
				"Case[25]=-1\r\n" + 
				"Case[26]=-1\r\n" + 
				"Case[27]=-1\r\n" + 
				"Case[28]=-1\r\n" + 
				"Case[29]=-1\r\n" + 
				"Case[30]=-1\r\n" + 
				"Case[31]=-1\r\n" + 
				"Case[32]=-1\r\n" + 
				"Case[33]=-1\r\n" + 
				"Case[34]=-1\r\n" + 
				"Case[35]=-1\r\n" + 
				"Case[36]=-1\r\n" + 
				"Case[37]=-1\r\n" + 
				"Case[38]=-1\r\n" + 
				"Case[39]=-1\r\n" + 
				"Case[40]=-1\r\n" + 
				"Case[41]=-1\r\n" + 
				"Case[42]=-1\r\n" + 
				"Case[43]=-1\r\n" + 
				"Case[44]=-1\r\n" + 
				"Case[45]=-1\r\n" + 
				"Case[46]=-1\r\n" + 
				"Case[47]=-1\r\n" + 
				"Case[48]=-1\r\n" + 
				"Case[49]=-1\r\n" + 
				"Case[50]=-1\r\n" + 
				"Case[51]=-1\r\n" + 
				"Case[52]=-1\r\n" + 
				"Case[53]=-1\r\n" + 
				"Case[54]=-1\r\n" + 
				"Case[55]=-1\r\n" + 
				"Case[56]=-1\r\n" + 
				"Case[57]=-1\r\n" + 
				"Case[58]=-1\r\n" + 
				"Case[59]=-1\r\n" + 
				"Case[60]=-1\r\n" + 
				"Case[61]=-1\r\n" + 
				"Case[62]=-1\r\n" + 
				"Case[63]=-1\r\n" + 
				"Case[64]=-1\r\n" + 
				"Case[65]=-1\r\n" + 
				"Case[66]=-1\r\n" + 
				"Case[67]=-1\r\n" + 
				"Case[68]=-1\r\n" + 
				"Case[69]=-1\r\n" + 
				"Case[70]=-1\r\n" + 
				"Case[71]=-1\r\n" + 
				"Case[72]=-1\r\n" + 
				"Case[73]=-1\r\n" + 
				"Case[74]=-1\r\n" + 
				"Case[75]=-1\r\n" + 
				"Case[76]=-1\r\n" + 
				"Case[77]=-1\r\n" + 
				"Case[78]=-1\r\n" + 
				"Case[79]=-1\r\n" + 
				"Case[80]=-1\r\n" + 
				"Case[81]=-1\r\n" + 
				"Case[82]=-1\r\n" + 
				"Case[83]=-1\r\n" + 
				"Case[84]=-1\r\n" + 
				"Case[85]=-1\r\n" + 
				"Case[86]=-1\r\n" + 
				"Case[87]=-1\r\n" + 
				"Case[88]=-1\r\n" + 
				"Case[89]=-1\r\n" + 
				"Case[90]=-1\r\n" + 
				"Case[91]=-1\r\n" + 
				"Case[92]=-1\r\n" + 
				"Case[93]=-1\r\n" + 
				"Case[94]=-1\r\n" + 
				"Case[95]=-1\r\n" + 
				"Case[96]=-1\r\n" + 
				"Case[97]=-1\r\n" + 
				"Case[98]=-1\r\n" + 
				"Case[99]=-1\r\n" + 
				"Processus termine\r\n" + 
				"Processus de PID 1 est sorti de la file\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Demande d'utiliser l'imprimante\r\n" + 
				"Le process de pid 1occupe la ressource xxxxxx pour une duree de 5 secondes\r\n" + 
				"Creation du processus 0 de taille 10 et de niveau priorite 1 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"en cours...\r\n" + 
				"Liberation de la ressource xxxx par le processus 0\r\n" + 
				"Exception in thread \"Thread-7\" java.lang.NullPointerException\r\n" + 
				"	at Sofware.GestionRessource.run(GestionRessource.java:34)\r\n" + 
				"Creation du processus 4 de taille 20 et de niveau priorite 4 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Espace suffisant\r\n" + 
				"processus de PID generer4\r\n" + 
				"Etat de la moire. Les cases ayant pour valuer -1 sont vides\r\n" + 
				"Case[0]=0\r\n" + 
				"4\r\n" + 
				"Case[1]=0\r\n" + 
				"Case[2]=0\r\n" + 
				"Case[3]=0\r\n" + 
				"Case[4]=0\r\n" + 
				"Case[5]=0\r\n" + 
				"Case[6]=0\r\n" + 
				"Case[7]=0\r\n" + 
				"Case[8]=0\r\n" + 
				"Case[9]=0\r\n" + 
				"Case[10]=1\r\n" + 
				"Case[11]=4\r\n" + 
				"Case[12]=4\r\n" + 
				"Case[13]=4\r\n" + 
				"Case[14]=4\r\n" + 
				"Case[15]=4\r\n" + 
				"Case[16]=4\r\n" + 
				"Case[17]=4\r\n" + 
				"Case[18]=4\r\n" + 
				"Case[19]=4\r\n" + 
				"Case[20]=4\r\n" + 
				"Case[21]=4\r\n" + 
				"Case[22]=4\r\n" + 
				"Case[23]=4\r\n" + 
				"Case[24]=4\r\n" + 
				"Case[25]=4\r\n" + 
				"Case[26]=4\r\n" + 
				"Case[27]=4\r\n" + 
				"Case[28]=4\r\n" + 
				"Case[29]=4\r\n" + 
				"Case[30]=4\r\n" + 
				"Case[31]=-1\r\n" + 
				"Case[32]=-1\r\n" + 
				"Case[33]=-1\r\n" + 
				"Case[34]=-1\r\n" + 
				"Case[35]=-1\r\n" + 
				"Case[36]=-1\r\n" + 
				"Case[37]=-1\r\n" + 
				"Case[38]=-1\r\n" + 
				"Case[39]=-1\r\n" + 
				"Case[40]=-1\r\n" + 
				"Case[41]=-1\r\n" + 
				"Case[42]=-1\r\n" + 
				"Case[43]=-1\r\n" + 
				"Case[44]=-1\r\n" + 
				"Case[45]=-1\r\n" + 
				"Case[46]=-1\r\n" + 
				"Case[47]=-1\r\n" + 
				"Case[48]=-1\r\n" + 
				"Case[49]=-1\r\n" + 
				"Case[50]=-1\r\n" + 
				"Case[51]=-1\r\n" + 
				"Case[52]=-1\r\n" + 
				"Case[53]=-1\r\n" + 
				"Case[54]=-1\r\n" + 
				"Case[55]=-1\r\n" + 
				"Case[56]=-1\r\n" + 
				"Case[57]=-1\r\n" + 
				"Case[58]=-1\r\n" + 
				"Case[59]=-1\r\n" + 
				"Case[60]=-1\r\n" + 
				"Case[61]=-1\r\n" + 
				"Case[62]=-1\r\n" + 
				"Case[63]=-1\r\n" + 
				"Case[64]=-1\r\n" + 
				"Case[65]=-1\r\n" + 
				"Case[66]=-1\r\n" + 
				"Case[67]=-1\r\n" + 
				"Case[68]=-1\r\n" + 
				"Case[69]=-1\r\n" + 
				"Case[70]=-1\r\n" + 
				"Case[71]=-1\r\n" + 
				"Case[72]=-1\r\n" + 
				"Case[73]=-1\r\n" + 
				"Case[74]=-1\r\n" + 
				"Case[75]=-1\r\n" + 
				"Case[76]=-1\r\n" + 
				"Case[77]=-1\r\n" + 
				"Case[78]=-1\r\n" + 
				"Case[79]=-1\r\n" + 
				"Case[80]=-1\r\n" + 
				"Case[81]=-1\r\n" + 
				"Case[82]=-1\r\n" + 
				"Case[83]=-1\r\n" + 
				"Case[84]=-1\r\n" + 
				"Case[85]=-1\r\n" + 
				"Case[86]=-1\r\n" + 
				"Case[87]=-1\r\n" + 
				"Case[88]=-1\r\n" + 
				"Case[89]=-1\r\n" + 
				"Case[90]=-1\r\n" + 
				"Case[91]=-1\r\n" + 
				"Case[92]=-1\r\n" + 
				"Case[93]=-1\r\n" + 
				"Case[94]=-1\r\n" + 
				"Case[95]=-1\r\n" + 
				"Case[96]=-1\r\n" + 
				"Case[97]=-1\r\n" + 
				"Case[98]=-1\r\n" + 
				"Case[99]=-1\r\n" + 
				"Processus termine\r\n" + 
				"Processus de PID 4 est sorti de la file\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 0 de taille 10 et de niveau priorite 1 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Liberation de la ressource xxxx par le processus 1\r\n" + 
				"Exception in thread \"Thread-9\" java.lang.NullPointerException\r\n" + 
				"	at Sofware.GestionRessource.run(GestionRessource.java:34)\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 3 de taille 12 et de niveau priorite 2 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Espace suffisant\r\n" + 
				"processus de PID generer3\r\n" + 
				"Etat de la moire. Les cases ayant pour valuer -1 sont vides\r\n" + 
				"Case[0]=0\r\n" + 
				"Case[1]=0\r\n" + 
				"Case[2]=0\r\n" + 
				"Case[3]=0\r\n" + 
				"Case[4]=0\r\n" + 
				"Case[5]=0\r\n" + 
				"Case[6]=0\r\n" + 
				"Case[7]=0\r\n" + 
				"Case[8]=0\r\n" + 
				"Case[9]=0\r\n" + 
				"Case[10]=1\r\n" + 
				"Case[11]=4\r\n" + 
				"Case[12]=3\r\n" + 
				"Case[13]=3\r\n" + 
				"Case[14]=3\r\n" + 
				"Case[15]=3\r\n" + 
				"Case[16]=3\r\n" + 
				"Case[17]=3\r\n" + 
				"Case[18]=3\r\n" + 
				"Case[19]=3\r\n" + 
				"Case[20]=3\r\n" + 
				"Case[21]=3\r\n" + 
				"Case[22]=3\r\n" + 
				"Case[23]=3\r\n" + 
				"Case[24]=-1\r\n" + 
				"Case[25]=-1\r\n" + 
				"Case[26]=-1\r\n" + 
				"Case[27]=-1\r\n" + 
				"Case[28]=-1\r\n" + 
				"Case[29]=-1\r\n" + 
				"Case[30]=-1\r\n" + 
				"Case[31]=-1\r\n" + 
				"Case[32]=-1\r\n" + 
				"Case[33]=-1\r\n" + 
				"Case[34]=-1\r\n" + 
				"Case[35]=-1\r\n" + 
				"Case[36]=-1\r\n" + 
				"Case[37]=-1\r\n" + 
				"Case[38]=-1\r\n" + 
				"Case[39]=-1\r\n" + 
				"Case[40]=-1\r\n" + 
				"Case[41]=-1\r\n" + 
				"Case[42]=-1\r\n" + 
				"Case[43]=-1\r\n" + 
				"Case[44]=-1\r\n" + 
				"Case[45]=-1\r\n" + 
				"Case[46]=-1\r\n" + 
				"Case[47]=-1\r\n" + 
				"Case[48]=-1\r\n" + 
				"Case[49]=-1\r\n" + 
				"Case[50]=-1\r\n" + 
				"Case[51]=-1\r\n" + 
				"Case[52]=-1\r\n" + 
				"Case[53]=-1\r\n" + 
				"Case[54]=-1\r\n" + 
				"Case[55]=-1\r\n" + 
				"Case[56]=-1\r\n" + 
				"Case[57]=-1\r\n" + 
				"Case[58]=-1\r\n" + 
				"Case[59]=-1\r\n" + 
				"Case[60]=-1\r\n" + 
				"Case[61]=-1\r\n" + 
				"Case[62]=-1\r\n" + 
				"Case[63]=-1\r\n" + 
				"Case[64]=-1\r\n" + 
				"Case[65]=-1\r\n" + 
				"Case[66]=-1\r\n" + 
				"Case[67]=-1\r\n" + 
				"Case[68]=-1\r\n" + 
				"Case[69]=-1\r\n" + 
				"Case[70]=-1\r\n" + 
				"Case[71]=-1\r\n" + 
				"Case[72]=-1\r\n" + 
				"Case[73]=-1\r\n" + 
				"Case[74]=-1\r\n" + 
				"Case[75]=-1\r\n" + 
				"Case[76]=-1\r\n" + 
				"Case[77]=-1\r\n" + 
				"Case[78]=-1\r\n" + 
				"Case[79]=-1\r\n" + 
				"Case[80]=-1\r\n" + 
				"Case[81]=-1\r\n" + 
				"Case[82]=-1\r\n" + 
				"Case[83]=-1\r\n" + 
				"Case[84]=-1\r\n" + 
				"Case[85]=-1\r\n" + 
				"Case[86]=-1\r\n" + 
				"Case[87]=-1\r\n" + 
				"Case[88]=-1\r\n" + 
				"Case[89]=-1\r\n" + 
				"Case[90]=-1\r\n" + 
				"Case[91]=-1\r\n" + 
				"Case[92]=-1\r\n" + 
				"Case[93]=-1\r\n" + 
				"Case[94]=-1\r\n" + 
				"Case[95]=-1\r\n" + 
				"Case[96]=-1\r\n" + 
				"Case[97]=-1\r\n" + 
				"Case[98]=-1\r\n" + 
				"Case[99]=-1\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Utilisation du clavier\r\n" + 
				"3\r\n" + 
				"Le process de pid 3occupe la ressource xxxxxx pour une duree de 5 secondes\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 4 de taille 20 et de niveau priorite 4 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 3 de taille 12 et de niveau priorite 2 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus termine\r\n" + 
				"Processus de PID 3 est sorti de la file\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus termine\r\n" + 
				"Processus de PID 3 est sorti de la file\r\n" + 
				"Liberation de la ressource xxxx par le processus 3\r\n" + 
				"Exception in thread \"Thread-11\" java.lang.NullPointerException\r\n" + 
				"	at Sofware.GestionRessource.run(GestionRessource.java:34)\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 0 de taille 10 et de niveau priorite 1 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Processus termine\r\n" + 
				"Processus de PID 3 est sorti de la file\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Demande d'acceder a l'ecran\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 0 de taille 10 et de niveau priorite 1 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Demande d'utiliser l'imprimante\r\n" + 
				"Le process de pid 3occupe la ressource xxxxxx pour une duree de 5 secondes\r\n" + 
				"Creation du processus 4 de taille 20 et de niveau priorite 4 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Creation du processus 4 de taille 20 et de niveau priorite 4 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Liberation de la ressource xxxx par le processus 3Exception in thread \"Thread-16\" \r\n" + 
				"java.lang.NullPointerException\r\n" + 
				"	at Sofware.GestionRessource.run(GestionRessource.java:34)\r\n" + 
				"Creation du processus 2 de taille 15 et de niveau priorite 5 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Espace suffisant\r\n" + 
				"processus de PID generer2\r\n" + 
				"Etat de la moire. Les cases ayant pour valuer -1 sont vides\r\n" + 
				"2\r\n" + 
				"Case[0]=0\r\n" + 
				"Case[1]=0\r\n" + 
				"Case[2]=0\r\n" + 
				"Case[3]=0\r\n" + 
				"Case[4]=0\r\n" + 
				"Case[5]=0\r\n" + 
				"Case[6]=0\r\n" + 
				"Case[7]=0\r\n" + 
				"Case[8]=0\r\n" + 
				"Case[9]=0\r\n" + 
				"Case[10]=1\r\n" + 
				"Case[11]=4\r\n" + 
				"Case[12]=3\r\n" + 
				"Case[13]=2\r\n" + 
				"Case[14]=2\r\n" + 
				"Case[15]=2\r\n" + 
				"Case[16]=2\r\n" + 
				"Case[17]=2\r\n" + 
				"Case[18]=2\r\n" + 
				"Case[19]=2\r\n" + 
				"Case[20]=2\r\n" + 
				"Case[21]=2\r\n" + 
				"Case[22]=2\r\n" + 
				"Case[23]=2\r\n" + 
				"Case[24]=2\r\n" + 
				"Case[25]=2\r\n" + 
				"Case[26]=2\r\n" + 
				"Case[27]=2\r\n" + 
				"Case[28]=-1\r\n" + 
				"Case[29]=-1\r\n" + 
				"Case[30]=-1\r\n" + 
				"Case[31]=-1\r\n" + 
				"Case[32]=-1\r\n" + 
				"Case[33]=-1\r\n" + 
				"Case[34]=-1\r\n" + 
				"Case[35]=-1\r\n" + 
				"Case[36]=-1\r\n" + 
				"Case[37]=-1\r\n" + 
				"Case[38]=-1\r\n" + 
				"Case[39]=-1\r\n" + 
				"Case[40]=-1\r\n" + 
				"Case[41]=-1\r\n" + 
				"Case[42]=-1\r\n" + 
				"Case[43]=-1\r\n" + 
				"Case[44]=-1\r\n" + 
				"Case[45]=-1\r\n" + 
				"Case[46]=-1\r\n" + 
				"Case[47]=-1\r\n" + 
				"Case[48]=-1\r\n" + 
				"Case[49]=-1\r\n" + 
				"Case[50]=-1\r\n" + 
				"Case[51]=-1\r\n" + 
				"Case[52]=-1\r\n" + 
				"Case[53]=-1\r\n" + 
				"Case[54]=-1\r\n" + 
				"Case[55]=-1\r\n" + 
				"Case[56]=-1\r\n" + 
				"Case[57]=-1\r\n" + 
				"Case[58]=-1\r\n" + 
				"Case[59]=-1\r\n" + 
				"Case[60]=-1\r\n" + 
				"Case[61]=-1\r\n" + 
				"Case[62]=-1\r\n" + 
				"Case[63]=-1\r\n" + 
				"Case[64]=-1\r\n" + 
				"Case[65]=-1\r\n" + 
				"Case[66]=-1\r\n" + 
				"Case[67]=-1\r\n" + 
				"Case[68]=-1\r\n" + 
				"Case[69]=-1\r\n" + 
				"Case[70]=-1\r\n" + 
				"Case[71]=-1\r\n" + 
				"Case[72]=-1\r\n" + 
				"Case[73]=-1\r\n" + 
				"Case[74]=-1\r\n" + 
				"Case[75]=-1\r\n" + 
				"Case[76]=-1\r\n" + 
				"Case[77]=-1\r\n" + 
				"Case[78]=-1\r\n" + 
				"Case[79]=-1\r\n" + 
				"Case[80]=-1\r\n" + 
				"Case[81]=-1\r\n" + 
				"Case[82]=-1\r\n" + 
				"Case[83]=-1\r\n" + 
				"Case[84]=-1\r\n" + 
				"Case[85]=-1\r\n" + 
				"Case[86]=-1\r\n" + 
				"Case[87]=-1\r\n" + 
				"Case[88]=-1\r\n" + 
				"Case[89]=-1\r\n" + 
				"Case[90]=-1\r\n" + 
				"Case[91]=-1\r\n" + 
				"Case[92]=-1\r\n" + 
				"Case[93]=-1\r\n" + 
				"Case[94]=-1\r\n" + 
				"Case[95]=-1\r\n" + 
				"Case[96]=-1\r\n" + 
				"Case[97]=-1\r\n" + 
				"Case[98]=-1\r\n" + 
				"Case[99]=-1\r\n" + 
				"Processus termine\r\n" + 
				"Processus de PID 2 est sorti de la file\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Creation du processus 2 de taille 15 et de niveau priorite 5 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Processus en train d'executer\r\n" + 
				"Utilisation du clavier\r\n" + 
				"Le process de pid 2occupe la ressource xxxxxx pour une duree de 5 secondes\r\n" + 
				"Creation du processus 2 de taille 15 et de niveau priorite 5 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Creation du processus 4 de taille 20 et de niveau priorite 4 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Liberation de la ressource xxxx par le processus 2\r\n" + 
				"Exception in thread \"Thread-18\" java.lang.NullPointerException\r\n" + 
				"	at Sofware.GestionRessource.run(GestionRessource.java:34)\r\n" + 
				"Creation du processus 0 de taille 10 et de niveau priorite 1 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Creation du processus 3 de taille 12 et de niveau priorite 2 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"Creation du processus 3 de taille 12 et de niveau priorite 2 \r\n" + 
				"Creation d'un nouveau processus\r\n" + 
				"Ce processus est deja charge\r\n" + 
				"";
		final Text text = new Text(10, 20, "");

		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(70000));
			}

			protected void interpolate(double frac) {
				final int length = content.length();
				final int n = Math.round(length * (float) frac);
				text.setText(content.substring(0, n));
			}

		};

		animation.play();
		return text;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
