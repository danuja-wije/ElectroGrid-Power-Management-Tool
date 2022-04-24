package model;

import java.util.List;

public class Interruption {
	private int id;
	private String inType;
	private String title;
	private String description;
	private String interruptionStartDate;
	private String interruptionEndDate;
	private String[] efectedList;
	private String approval;
	private String timeStamp;
	
	public Interruption(String inType, String title, String description, String interruptionStartDate,
			String interruptionEndDate, String[] efectedList, String approval) {
		super();
		this.inType = inType;
		this.title = title;
		this.description = description;
		this.interruptionStartDate = interruptionStartDate;
		this.interruptionEndDate = interruptionEndDate;

		this.efectedList = efectedList;
		this.approval = approval;
	}

	public int getId() {
		return id;
	}

	public String getInType() {
		return inType;
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

	public String[] getEfectedList() {
		return efectedList;
	}

	public String getApproval() {
		return approval;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
