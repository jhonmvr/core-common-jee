package com.relative.core.util.mail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MailMessageDefinition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7060040575836451001L;
	private String subject;
	private List<String> froms;
	private String contentStr;
	private Date sentDate;
	private Date receivedDate;
	
	
	
	
	public MailMessageDefinition(String subject, List<String> froms, String contentStr, Date sentDate,
			Date receivedDate) {
		super();
		this.subject = subject;
		this.froms = froms;
		this.contentStr = contentStr;
		this.sentDate = sentDate;
		this.receivedDate = receivedDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getFroms() {
		return froms;
	}
	public void setFroms(List<String> from) {
		this.froms = from;
	}
	public String getContentStr() {
		return contentStr;
	}
	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}
	
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}



	public static class Builder{
		private String subject;
		private List<String> froms;
		private String contentStr;
		private Date sentDate;
		private Date receivedDate;
		
		
		public Builder subject(final String subject) {
			this.subject=subject;
			return this;
		}
		
		public Builder froms( final List<String> froms) {
			this.froms=froms;
			return this;
		}
		public Builder contentStr(final String contentStr) {
			this.contentStr=contentStr;
			return this;
		}

		public Builder sentDate(final Date sentDate) {
			this.sentDate=sentDate;
			return this;
		}
		
		public Builder receivedDate(final Date receivedDate) {
			this.receivedDate=receivedDate;
			return this;
		}
		
		public MailMessageDefinition build() {
			return new MailMessageDefinition( subject,  froms,  contentStr,sentDate,receivedDate);
		}
	}
	

}
