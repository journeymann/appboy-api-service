/**
 * 
 */
package com.flowers.services.appboy.command;

import javax.validation.constraints.NotNull;

import com.flowers.services.appboy.network.Communicate;
import com.flowers.services.appboy.request.Request;

/**
 * @author cgordon
 * @created 08/02/2017
 * @version 1.0
 * 
 *  <p>
 *  
 *  This Java class defines SendCommand implementation of the Command interface
 * 
 *  <p> 
 */
public class SendGetCommand implements Command{

	@NotNull private Request request;
	
	public SendGetCommand(Request request){
		this.request = request;
	}	

	public void execute() {
		Communicate comm = new Communicate();
		comm.AsyncGet(request);		
	}

}
