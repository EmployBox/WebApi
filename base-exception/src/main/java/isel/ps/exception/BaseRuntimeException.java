/**
 * 
 */
package isel.ps.exception;

import isel.ps.exception.legacy.codes.ErrorCodes;

/**
 * @author davis.dun
 *
 */
public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -4177560721261518876L;
	
	private String errorCode;
    private StringBuffer errorCodeMessage = new StringBuffer();
    private boolean logIssue;

	/**
	 * 
	 */
	public BaseRuntimeException() {
		this.errorCode= ErrorCodes.INTERNAL_ERROR;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BaseRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.errorCode= ErrorCodes.INTERNAL_ERROR;
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public BaseRuntimeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		this.errorCode= ErrorCodes.INTERNAL_ERROR;
	}

    public BaseRuntimeException(Throwable cause) {
        super(cause);
        this.errorCode= ErrorCodes.INTERNAL_ERROR;
    }

    public BaseRuntimeException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String message, String errorCode) {
        super(message);
        this.errorCodeMessage.append(message);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String message, String errorCode, boolean logIssue) {
        super(message);
        this.errorCode = errorCode;
        this.logIssue = logIssue;
        this.errorCodeMessage.append(message);
    }

    public BaseRuntimeException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorCodeMessage.append(message);
    }

    public BaseRuntimeException(String message, Throwable cause, String errorCode, boolean logIssue) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorCodeMessage.append(message);
        this.logIssue = logIssue;
    }

    public BaseRuntimeException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(Throwable cause, String errorCode, boolean logIssue) {
        super(cause);
        this.errorCode = errorCode;
        this.logIssue = logIssue;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorCodeMessage() {
        return errorCodeMessage.toString();
    }
    public void appendErrorCodeMessage(String errorCodeMessage) {
    	
    	if(errorCodeMessage==null)
    		return;

        this.errorCodeMessage.append(errorCodeMessage).append("; ");
    }

    public boolean logIssue() {
        return logIssue;
    }

    public void setLogIssue(boolean logIssue) {
        this.logIssue = logIssue;
    }
    
    public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
