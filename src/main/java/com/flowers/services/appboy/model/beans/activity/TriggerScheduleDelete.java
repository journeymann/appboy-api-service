/**
 * 
 */
package com.flowers.services.appboy.model.beans.activity;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author cgordon
 * @created 08/02/2017
 * @version 1.0
 *
 * * @see <a href="https://www.appboy.com/documentation/REST_API/#delete-scheduled-api-trigger-campaign">Appboy scheduled delete</a>
 *
 *{
 *  "app_group_id": (required, string) see App Group Identifier below,
 *  "campaign_id": (required, string) see Campaign Identifier,
 *  "schedule_id": (required, string) the schedule_id to delete (obtained from the response to create schedule)
 *}
 *
 */
@JsonRootName(value = "trigger_schedule_delete")
public class TriggerScheduleDelete extends Activity{
	@NotNull
	@JsonProperty(value="app_group_id",required=true)
	private String app_group_id;

	@NotNull
	@JsonProperty(value="campaign_id",required=true)
	private String campaign_id;

	@NotNull
	@JsonProperty(value="schedule_id",required=true)
	private String schedule_id;
	
	public TriggerScheduleDelete(){
		propertyKeyRef = "trigger.schedule.delete";
	}
		
	/**
	 * @return the app_group_id
	 */
	public String getApp_group_id() {
		return app_group_id;
	}

	/**
	 * @param app_group_id the app_group_id to set
	 */
	public void setApp_group_id(String app_group_id) {
		this.app_group_id = app_group_id;
	}

	/**
	 * @return the campaign_id
	 */
	public String getCampaign_id() {
		return campaign_id;
	}

	/**
	 * @param campaign_id the campaign_id to set
	 */
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	/**
	 * @return the schedule_id
	 */
	public String getSchedule_id() {
		return schedule_id;
	}

	/**
	 * @param schedule_id the schedule_id to set
	 */
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}
			  
	/**
	 * @return the propertyKeyRef
	 */
	public String getPropertyKeyRef() {
		return propertyKeyRef;
	}
			  
}
