package util;


import java.io.Serializable;

/**
 * Wrapper object for client server interactions.
 */
public class CommandTransfer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String sender = null;
	private String receiver = null;
	
	// Data being transmitted, for example User information 
	// encapsulated in an object.
	private Object data = null;
	
	// indicator of whether an instruction has been
	// successful or not.
	private boolean flag = false;
	
	// protocol to be carried out.
	private String cmd = null;
	
	// Human readable message displaying the result of a 
	// given process.
	private String result = null;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
}
