package com.relative.core.util.mail;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import com.relative.core.util.enums.EmailSecurityTypeEnum;

public class EmailDefinition {
	
	private String smtpHostServer;
	private Session session ;
	private String fromEmail;
	private String replyTo;
	private String user;
    private String password;
    private String port;
    private String sfPort;
    private String subject;
    private String message;
    
    private Boolean auth=Boolean.FALSE;
    private List<String> tos;
    private List<String> ccs;
    private EmailSecurityTypeEnum emailSecurityType;
    
    private String mailFolder;
    private Date dateRangeBegin;
    private Date dateRangeEnd;
    
    private Map<String,byte[]> attachments;
    private Map<String,String> urlAttachments;
    private Map<String,String> base64Attachments;
    
    private byte[] image;
    private String urlImage;
    private String base64Image;
    
    private Boolean hasFiles;

    
    public EmailDefinition() {}
    
	public EmailDefinition(String smtpHostServer, Session session, String fromEmail, String replyTo, String user,
			String password, String port, String sfPort, String subject, String message, Boolean auth, List<String> tos,
			List<String> ccs, EmailSecurityTypeEnum emailSecurityType, String mailFolder, Date dateRangeBegin,
			Date dateRangeEnd,Map<String,byte[]> attachments,Map<String,String> urlAttachments,Map<String,String> base64Attachments, 
			byte[] image,String urlImage,String base64Image, Boolean hasFiles) {
		super();
		this.smtpHostServer = smtpHostServer;
		this.session = session;
		this.fromEmail = fromEmail;
		this.replyTo = replyTo;
		this.user = user;
		this.password = password;
		this.port = port;
		this.sfPort = sfPort;
		this.subject = subject;
		this.message = message;
		this.auth = auth;
		this.tos = tos;
		this.ccs = ccs;
		this.emailSecurityType = emailSecurityType;
		this.mailFolder = mailFolder;
		this.dateRangeBegin = dateRangeBegin;
		this.dateRangeEnd = dateRangeEnd;
		this.attachments=attachments;
		this.urlAttachments=urlAttachments;
    this.base64Attachments=base64Attachments;                                       
		this.image=image;
    this.base64Image=base64Image;
		this.urlImage=urlImage;
		this.hasFiles=hasFiles;
	}

	public String getSmtpHostServer() {
		return smtpHostServer;
	}

	public void setSmtpHostServer(String smtpHostServer) {
		this.smtpHostServer = smtpHostServer;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public List<String> getTos() {
		return tos;
	}

	public void setTos(List<String> tos) {
		this.tos = tos;
	}

	public List<String> getCcs() {
		return ccs;
	}

	public void setCcs(List<String> ccs) {
		this.ccs = ccs;
	}
	
	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSfPort() {
		return sfPort;
	}

	public void setSfPort(String sfPort) {
		this.sfPort = sfPort;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

  public EmailSecurityTypeEnum getEmailSecurityType() {
		return emailSecurityType;
	}

	public void setEmailSecurityType(EmailSecurityTypeEnum emailSecurityType) {
		this.emailSecurityType = emailSecurityType;
	}

	
	
	public String getMailFolder() {
		return mailFolder;
	}

	public void setMailFolder(String mailFolder) {
		this.mailFolder = mailFolder;
	}

	public Date getDateRangeBegin() {
		return dateRangeBegin;
	}

	public void setDateRangeBegin(Date dateRangeBegin) {
		this.dateRangeBegin = dateRangeBegin;
	}

	public Date getDateRangeEnd() {
		return dateRangeEnd;
	}

	public void setDateRangeEnd(Date dateRangeEnd) {
		this.dateRangeEnd = dateRangeEnd;
	}



	public Map<String, byte[]> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, byte[]> attachments) {
		this.attachments = attachments;
	}

	public Boolean getHasFiles() {
		return hasFiles;
	}

	public void setHasFiles(Boolean hasFiles) {
		this.hasFiles = hasFiles;
	}

	public Map<String,String> getUrlAttachments() {
		return urlAttachments;
	}

	public void setUrlAttachments(Map<String,String> urlAttachments) {
		this.urlAttachments = urlAttachments;
	}
  
  public Map<String,String> getBase64Attachments() {
		return base64Attachments;
	}

	public void setBase64Attachments(Map<String,String> base64Attachments) {
		this.base64Attachments = base64Attachments;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
  
  public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}



	public static class Builder{
		private String smtpHostServer;
		private  Session session ;
		private String fromEmail;
		private String replyTo;
		private String user;
	    private String password;
	    private String port;
	    private String sfPort;
	    private String subject;
	    private String message;
	    private Boolean auth;
	    private EmailSecurityTypeEnum emailSecurityType;
	    private List<String> tos;
	    private List<String> ccs;
	    private String mailFolder;
	    private Date dateRangeBegin;
	    private Date dateRangeEnd;
	    private Map<String,byte[]> attachments;
	    private Map<String,String> urlAttachments;
      private Map<String,String> base64Attachments;
	    private byte[] image;
	    private String urlImage;
	    private Boolean hasFiles;
      private String base64Image;

      
      public Builder() {
  			//ND
  		}
	    
	    public Builder smtpHostServer( final String smtpHostServer ) {
	    	this.smtpHostServer=smtpHostServer;
	    	return this;
	    }
	    
	    public Builder session( final Session session ) {
	    	this.session=session;
	    	return this;
	    }
	    
	    public Builder fromEmail( final String fromEmail ) {
	    	this.fromEmail=fromEmail;
	    	return this;
	    }
	    
	    public Builder replyTo( final String replyTo ) {
	    	this.replyTo=replyTo;
	    	return this;
	    }
	    
	    public Builder user( final String user ) {
	    	this.user=user;
	    	return this;
	    }
	    
	    public Builder password( final String password ) {
	    	this.password=password;
	    	return this;
	    }
	    
	    public Builder port( final String port ) {
	    	this.port=port;
	    	return this;
	    }
	    
	    public Builder sfPort( final String sfPort ) {
	    	this.sfPort=sfPort;
	    	return this;
	    }
	    
	    public Builder subject( final String subject ) {
	    	this.subject=subject;
	    	return this;
	    }
	    
	    public Builder message( final String message ) {
	    	this.message=message;
	    	return this;
	    }
	    
	    public Builder auth( final Boolean auth ) {
	    	this.auth=auth;
	    	return this;
	    }
	    
	    public Builder tos( final List<String> tos ) {
	    	this.tos=tos;
	    	return this;
	    }
	    
	    public Builder ccs( final List<String> ccs ) {
	    	this.ccs=ccs;
	    	return this;
	    }
	            
      public Builder emailSecurityType( final EmailSecurityTypeEnum emailSecurityType ) {
	    	this.emailSecurityType=emailSecurityType;
	    	return this;
	    }
      
      public Builder mailFolder( final String mailFolder ) {
	    	this.mailFolder=mailFolder;
	    	return this;
	    }
      
      public Builder dateRangeBegin( final Date dateRangeBegin ) {
	    	this.dateRangeBegin=dateRangeBegin;
	    	return this;
	    }
      
      public Builder dateRangeEnd( final Date dateRangeEnd ) {
	    	this.dateRangeEnd=dateRangeEnd;
	    	return this;
	    }
      
      public Builder attachments( final Map<String, byte[]> attachments ) {
	    	this.attachments=attachments;
	    	return this;
	    }
      
      public Builder urlAttachments( final Map<String,String> urlAttachments ) {
	    	this.urlAttachments=urlAttachments;
	    	return this;
	    }
      
      public Builder base64Attachments( final Map<String,String> base64Attachments ) {
	    	this.base64Attachments=base64Attachments;
	    	return this;
	    }
      
      public Builder image( final byte[] image ) {
	    	this.image=image;
	    	return this;
	    }
      
      public Builder base64Image( final String base64Image ) {
	    	this.base64Image=base64Image;
	    	return this;
	    }
      
      public Builder urlImage( final String urlImage ) {
	    	this.urlImage=urlImage;
	    	return this;
	    }
      
      public Builder hasFiles( final Boolean hasFiles ) {
	    	this.hasFiles=hasFiles;
	    	return this;
	    }
	    
	    public EmailDefinition build(){
	    	return new EmailDefinition(smtpHostServer, session, fromEmail, replyTo, user, 
	    			password, port, sfPort, subject, message, auth, tos, ccs, emailSecurityType, 
	    			mailFolder, dateRangeBegin, dateRangeEnd, attachments,urlAttachments,base64Attachments, 
	    			image,urlImage,base64Image, hasFiles);
	    }
	    
	}

}
