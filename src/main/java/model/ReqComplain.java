package model;

public class ReqComplain {
	private String id;
	private String cutomerID;
	private String type;
	private String description;
	
	
	public ReqComplain(String id, String cutomerID, String type, String description) {
		super();
		this.id = id;
		this.cutomerID = cutomerID;
		this.type = type;
		this.description = description;
	}


	public String getId() {
		return id;
	}


	public String getCutomerID() {
		return cutomerID;
	}


	public String getType() {
		return type;
	}


	public String getDescription() {
		return description;
	}
}
