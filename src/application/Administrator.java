package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

import components.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class Administrator {

	// check the correspondence of the entered data
	public static boolean boolEdit(boolean bool, String nameTask, String description, String priority,
			String expirationDate, String doerName, String readinesMark) {
		bool = false;
		if (nameTask == null || "".equals(nameTask) || nameTask.matches("\\s+")
				|| (description == null || "".equals(description) || description.matches("\\s+"))
				|| (priority == null || "".equals(priority) || priority.matches("\\s+"))
				|| (expirationDate == null || "".equals(expirationDate) || expirationDate.matches("\\s+"))
				|| (doerName == null || "".equals(doerName) || doerName.matches("\\s+"))
				|| (readinesMark == null || "".equals(readinesMark) || readinesMark.matches("\\s+"))) {
			JOptionPane.showMessageDialog(null, "Необходимо заполнить все поля", "Ошибка", JOptionPane.ERROR_MESSAGE);
		} else {
			bool = true;
		}
		return bool;
	}

	// method of filling a table from a file
	public static ObservableList<Task> getTasksList(String dateNow, BufferedReader file) throws IOException {
		ObservableList<Task> list = FXCollections.<Task>observableArrayList();
		String[] element = new String[7];
		while (file.read() != -1) {
			element = file.readLine().split(" _ & _ ");
			list.add(new Task(element[0], element[1], element[2], element[3], element[4], element[5], element[6]));
		}
		return FXCollections.<Task>observableArrayList(list);
	}

	// The method of deleting a line from a file by creating a new file, deleting
	// the old one, rename new
	public static void deleteTask(ObservableList<Task> tasksList, TableView<Task> allTasksTable, File file)
			throws IOException {
		TableViewSelectionModel<Task> tsm = allTasksTable.getSelectionModel();
		if (tsm.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ни один элемент не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
		} else {
			int selectedIndex = tsm.getSelectedIndex();
			tasksList.remove(selectedIndex);
			try {
				FileReader primaryReader = new FileReader(file);
				File temporaryFile = new File("temporary file.txt");

				temporaryFile.createNewFile();

				BufferedReader bufferedPrimaryReader = new BufferedReader(primaryReader);
				LineNumberReader readerLineNumber = new LineNumberReader(bufferedPrimaryReader);

				FileWriter writerToTemporaryFile = new FileWriter(temporaryFile, true);
				BufferedWriter bufferedWriterToTemporaryFile = new BufferedWriter(writerToTemporaryFile);

				String oneLine;
				int number = 0;

				// write to a new file of lines except the selected
				while ((oneLine = readerLineNumber.readLine()) != null) {
					number = readerLineNumber.getLineNumber() - 1;
					if ((number) != selectedIndex) {
						bufferedWriterToTemporaryFile.write(oneLine + "\n");
					}
				}

				bufferedWriterToTemporaryFile.close();
				writerToTemporaryFile.close();
				bufferedPrimaryReader.close();
				readerLineNumber.close();
				primaryReader.close();
				file.delete();

				// I use the If-else block, because File.delete (); returns a boolean value, not
				// Exception
				// and nested try-catch, in my opinion does not make sense
				if (temporaryFile.renameTo(file)) {
					temporaryFile.delete();
				} else
					JOptionPane.showMessageDialog(null, "Ошибка. Данные не удалось перенести");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Ошибка в методе удаления строки.");
				e.printStackTrace();
			}
		}
	}

	// I take date of creation from system time (LocalDate)
	public static ObservableList<Task> addTaskInTable(ObservableList<Task> allTasks, TableView<Task> allTasksTable,
			String nameTask, String description, String priority, String dateOfCreation, String expirationDate,
			String doerName, String readinesMark, boolean bool) {
		if ((bool == boolEdit(bool, nameTask, description, priority, expirationDate, doerName, readinesMark)) == true) {
			allTasks.add(
					new Task(nameTask, description, priority, dateOfCreation, expirationDate, doerName, readinesMark));
			allTasksTable.setItems(allTasks);
			return allTasks;
		} else
			return null;
	}

	//
	public static void addStringInFile(javafx.scene.control.TextField nameTask,
			javafx.scene.control.TextField description, javafx.scene.control.TextField priority, String dateNow,
			javafx.scene.control.TextField expirationDate, javafx.scene.control.TextField doerName,
			javafx.scene.control.TextField readinesMark, BufferedReader bufferedFileReader) throws IOException {

		BufferedWriter bufferedPrimaryFileWriter = new BufferedWriter(new FileWriter("list.txt", true));

		String delimeter = " _ & _ ";
		String newTaskString = new String("\n " + nameTask.getText().toString() + delimeter
				+ description.getText().toString() + delimeter + priority.getText().toString() + delimeter + dateNow
				+ delimeter + expirationDate.getText().toString() + delimeter + doerName.getText().toString()
				+ delimeter + readinesMark.getText().toString());
		bufferedPrimaryFileWriter.write(newTaskString);
		bufferedPrimaryFileWriter.close();
		bufferedFileReader.close();

	}
}