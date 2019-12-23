package application;

public class Task {

	private String name;
	private String description;
	private String priority;
	private String dateOfCreation;
	private String expirationDate;
	private String doer;
	private String readinesMark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getDoer() {
		return doer;
	}

	public void setDoer(String doer) {
		this.doer = doer;
	}

	public String getReadinesMark() {
		return readinesMark;
	}

	public void setReadinesMark(String readinesMark) {
		this.readinesMark = readinesMark;
	}

	public Task(String name, String description, String priority, String dateNow, String expirationDate,
			String doerName, String readinesMark) {
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.dateOfCreation = dateNow;
		this.expirationDate = expirationDate;
		this.doer = doerName;
		this.readinesMark = readinesMark;
	}

	@Override
	public String toString() {
		return "Заголовок: " + name + ", Описание: " + description + ", Приоритет: " + priority + ", Дата Создания: "
				+ dateOfCreation + ", Срок Выполнения: " + expirationDate + ", Кто должен выполнить: " + doer
				+ ", Отметка о выполнении: " + readinesMark + "]";
	}

}
