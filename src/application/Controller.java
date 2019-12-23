package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Controller {

	private ObservableList<Task> tasksList = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane allPane;

	@FXML
	private Button deleteTask;

	@FXML
	private Button addTask;

	@FXML
	private TextField nameTask;

	@FXML
	private TextField description;

	@FXML
	private TextField priority;

	@FXML
	private TextField dateOfCreation;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField doerName;

	@FXML
	private TextField readinesMark;

	@FXML
	private TableView<Task> allTasksTable;

	@FXML
	private TableColumn<Task, String> nameColumn;

	@FXML
	private TableColumn<Task, String> descriptionColumn;

	@FXML
	private TableColumn<Task, String> prioritetyColumn;

	@FXML
	private TableColumn<Task, String> dateOfCreationColumn;

	@FXML
	private TableColumn<Task, String> expirationTimeColumn;

	@FXML
	private TableColumn<Task, String> doerNameColumn;

	@FXML
	private TableColumn<Task, String> readinesMarkColumn;

	@FXML
	void initialize() throws IOException {

		String dateNow = LocalDate.now().toString();
		File file = new File("list.txt");
		file.createNewFile();
		FileReader fileReader = new FileReader(file);
		BufferedReader listReader = new BufferedReader(fileReader);

		tasksList = TableViewHelper.getTasksList(dateNow, listReader);
		allTasksTable.setItems(tasksList);
		dateOfCreation.setText(LocalDate.now().toString());

		nameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
		prioritetyColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("priority"));
		dateOfCreationColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("dateOfCreation"));
		expirationTimeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("expirationDate"));
		doerNameColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("doer"));
		readinesMarkColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("readinesMark"));

		allTasksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		allTasksTable.setEditable(true);

		boolean bool = true;
		addTask.setOnAction(event -> {
			if (TableViewHelper.addTask(tasksList, allTasksTable, nameTask.getText(), description.getText(),
					priority.getText(), dateNow, expirationDate.getText(), doerName.getText(), readinesMark.getText(),
					bool) != null) {
				try {
					BufferedWriter listWriter = new BufferedWriter(new FileWriter("list.txt", true));
					String delimeter = " _ & _ ";
					String newTaskString = new String(nameTask.getText().toString() + delimeter
							+ description.getText().toString() + delimeter + priority.getText().toString() + delimeter
							+ dateNow.toString() + delimeter + expirationDate.getText().toString() + delimeter
							+ doerName.getText().toString() + delimeter + readinesMark.getText().toString());
					listWriter.write(newTaskString + "\n");
					listWriter.close();
					listReader.close();
				} catch (IOException e) {
					System.out.println("Ошибка записи в файл из полей");
					e.printStackTrace();
				}
				nameTask.setText(null);
				description.setText(null);
				priority.setText(null);
				dateOfCreation.setText(dateNow.toString());
				expirationDate.setText(null);
				doerName.setText(null);
				readinesMark.setText(null);
			}
		});

		deleteTask.setOnAction(event -> {
			try {
				TableViewHelper.deleteTask(tasksList, allTasksTable, file);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ошибка. Файл не найден");
				e.printStackTrace();
			}
		});
	}
}
