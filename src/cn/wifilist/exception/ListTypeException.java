package cn.wifilist.exception;

public class ListTypeException extends Exception{
	
	public ListTypeException(){
		super();
	}
	
	public ListTypeException(String message,Throwable cause){
		super(message,cause);
	}
	
	public ListTypeException(String message){
		super(message);
	}
	
	public ListTypeException(Throwable cause){
		super(cause);
	}
}
