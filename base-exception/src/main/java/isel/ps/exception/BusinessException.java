/**
 * 
 */
package isel.ps.exception;



import isel.ps.base.model.BusinessMessageModel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author davis.dun
 *
 */
public class BusinessException extends BaseRuntimeException {

	
	private static final long serialVersionUID = -3707041754279655257L;
	
	protected List<BusinessMessageModel> businessMessageModels;
	
	/**
	 * 
	 */
	public BusinessException(List<BusinessMessageModel> businessMessageModels) {
		Objects.requireNonNull(businessMessageModels);
		this.businessMessageModels = businessMessageModels;
	}

	/**
	 * @param arg0
	 */
	public BusinessException(String arg0, List<BusinessMessageModel> businessMessageModels) {
		super(arg0);
		Objects.requireNonNull(businessMessageModels);
		this.businessMessageModels = businessMessageModels;
	}

	/**
	 * @param arg0
	 */
	public BusinessException(Throwable arg0, List<BusinessMessageModel> businessMessageModels) {
		super(arg0);
		Objects.requireNonNull(businessMessageModels);
		this.businessMessageModels = businessMessageModels;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BusinessException(String arg0, Throwable arg1, List<BusinessMessageModel> businessMessageModels) {
		super(arg0, arg1);
		Objects.requireNonNull(businessMessageModels);
		this.businessMessageModels = businessMessageModels;
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public BusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3, List<BusinessMessageModel> businessMessageModels) {
		super(arg0, arg1, arg2, arg3);
		Objects.requireNonNull(businessMessageModels);
		this.businessMessageModels = businessMessageModels;
	}
	
	public List<BusinessMessageModel> getBusinessMessages() {
		return Collections.unmodifiableList(businessMessageModels);
	}
	
	/**
	 * Return all business message separated by a semicolon.  
	 * */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(businessMessageModels!=null)
			businessMessageModels.forEach(msg -> {sb.append(msg).append("; ");} );
		return sb.toString();
	}
	
	/**
	 * 
	 */
	protected BusinessException() {
		super();
	}

	/**
	 * @param arg0
	 */
	protected BusinessException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	protected BusinessException(Throwable arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	protected BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	protected BusinessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}
	
	public void issueMessage(Integer code) {
		super.setLogIssue(true);
		businessMessageModels.forEach(bm ->{
			if(bm.getCode()==code) {
				setErrorCode(code.toString());
				appendErrorCodeMessage(bm.getMessage());
			}	
		});
		
	}
	
}
