package isel.ps.base.model;


import java.util.Objects;

public class BusinessMessageModel extends BaseModel{
	
	private static final long serialVersionUID = 3129624070524999046L;
	
	private int code;
	private String message;
	
	public BusinessMessageModel(int code, String message) {
		Objects.requireNonNull(code);
		Objects.requireNonNull(message);
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	public String getCodeInStringType() {
		return String.valueOf(code);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "[" + code + "] " + message;
	}
	
}