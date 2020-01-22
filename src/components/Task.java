package components;

public class Task {

	private String name;
	private String description;
	private String priority;
	private String dateOfCreation;
	private String expirationDate;
	private String doer;
	private String readinesMark;

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

	@Override
	public String toString() {
		return "Заголовок: " + name + ", Описание: " + description + ", Приоритет: " + priority + ", Дата Создания: "
				+ dateOfCreation + ", Срок Выполнения: " + expirationDate + ", Кто должен выполнить: " + doer
				+ ", Отметка о выполнении: " + readinesMark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((doer == null) ? 0 : doer.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((readinesMark == null) ? 0 : readinesMark.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (dateOfCreation == null) {
			if (other.dateOfCreation != null)
				return false;
		} else if (!dateOfCreation.equals(other.dateOfCreation))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (doer == null) {
			if (other.doer != null)
				return false;
		} else if (!doer.equals(other.doer))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (readinesMark == null) {
			if (other.readinesMark != null)
				return false;
		} else if (!readinesMark.equals(other.readinesMark))
			return false;
		return true;
	}

}
