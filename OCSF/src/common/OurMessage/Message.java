package common.OurMessage;

import java.io.Serializable;
import java.util.ArrayList;

import common.Entities.User;

public class Message implements Serializable
{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private String whatToDo;
	private ArrayList<Object> data;
	
	public Message()
	{
		whatToDo = null;
		data = new ArrayList<>();
	}

	public Message(String whatToDo, Object data)
	{
		this.whatToDo= whatToDo;
		this.data = new ArrayList<>();
	}
	
	public String getWhatToDo()
	{
		return whatToDo;
	}
	
	public void setWhatToDo(String whatToDo)
	{
		this.whatToDo = whatToDo;
	}
	
	public ArrayList<Object> getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data.add(data);
	}
	
}