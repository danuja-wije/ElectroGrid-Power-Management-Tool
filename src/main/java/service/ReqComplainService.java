package service;

import java.util.ArrayList;

import model.ReqComplain;

public interface ReqComplainService {

	public String insertRequest(ReqComplain request);
	public ArrayList<ReqComplain> viewRequest(String id);
	public String updateRequest(String id, ReqComplain request);
	public String deleteRequest(String id);
}
