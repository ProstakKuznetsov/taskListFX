package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class TableViewHelper {

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

	// Метод получения данных в таблицу из текстового файла

	public static ObservableList<Task> getTasksList(String dateNow, BufferedReader file) throws IOException {
		ObservableList<Task> list = FXCollections.<Task>observableArrayList();
		String[] element = new String[7];
		while (file.read() != -1) {
			element = file.readLine().split(" _ & _ ");
			list.add(new Task(element[0], element[1], element[2], element[3], element[4], element[5], element[6]));
		}
		return FXCollections.<Task>observableArrayList(list);
	}

	// Метод удаления строки из файла путем создания нового файла, удаления старого,
	// переименования нового

	public static void deleteTask(ObservableList<Task> tasksList, TableView<Task> allTasksTable, File file)
			throws IOException {
		TableViewSelectionModel<Task> tsm = allTasksTable.getSelectionModel();
		if (tsm.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ни один элемент не выбран", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int selectedIndex = tsm.getSelectedIndex();
			tasksList.remove(selectedIndex);
			try {
				FileReader fileReader = new FileReader(file);
				File newFile = new File("list2.txt");
				newFile.createNewFile();
				BufferedReader listReader = new BufferedReader(fileReader);
				LineNumberReader bufReadLine = new LineNumberReader(listReader);
				String oneLine;

				FileWriter newFileWriter = new FileWriter(newFile, true);
				BufferedWriter bufNewFileWriter = new BufferedWriter(newFileWriter);

				int number = 0;
				while ((oneLine = bufReadLine.readLine()) != null) {
					number = bufReadLine.getLineNumber() - 1;
					if ((number) != selectedIndex) {
						bufNewFileWriter.write(oneLine + "\n");
					}
				}
				bufNewFileWriter.close();
				newFileWriter.close();
				listReader.close();
				bufReadLine.close();
				fileReader.close();
				file.delete();
				if (newFile.renameTo(file)) {
					newFile.delete();
				} else
					JOptionPane.showMessageDialog(null, "Ошибка. Данные не удалось перенести");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Ошибка в методе удаления строки.");
				e.printStackTrace();
			}
		}
	}

	/*
	 * Дату создания добавляю автоматически. Мне, как пользователю, так было бы
	 * удобнее.
	 */
	public static ObservableList<Task> addTask(ObservableList<Task> allTasks, TableView<Task> allTasksTable,
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
}
