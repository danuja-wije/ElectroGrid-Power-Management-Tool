package model;

import java.util.List;

public class Interruption {
	private int id;
	private String entType;
	private String title;
	private String description;
	private String interruptionStartDate;
	private String interruptionEndDate;
	private String startTime;
	private String endTime;
	private List<Employee>efectedList;
	private String approval;
	
	public Interruption(int id, String entType, String title, String description, String interruptionStartDate,
			String interruptionEndDate, String startTime, String endTime, List<Employee> efectedList, String approval) {
		super();
		this.id = id;
		this.entType = entType;
		this.title = title;
		this.description = description;
		this.interruptionStartDate = interruptionStartDate;
		this.interruptionEndDate = interruptionEndDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.efectedList = efectedList;
		this.approval = approval;
	}

	public int getId() {
		return id;
	}

	public String getEntType() {
		return entType;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getInterruptionStartDate() {
		return interruptionStartDate;
	}

	public String getInterruptionEndDate() {
		return interruptionEndDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public List<Employee> getEfectedList() {
		return efectedList;
	}

	public String getApproval() {
		return approval;
	}
	
}
