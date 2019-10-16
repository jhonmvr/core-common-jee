package com.relative.core.util.main;


public class Constantes {
	
	public static final String GLOBAL_APP_NAME="SEGUROS-SUCRE";
	
	public static final String  FILENAME_GLOBAL_PROPS="app-config.xml";
		
	public static final String ERROR_CODE_CREATE="001";
	public static final String ERROR_CODE_READ="002";
	public static final String ERROR_CODE_UPDATE="003";
	public static final String ERROR_CODE_DELETE="004";
	public static final String ERROR_CODE_CUSTOM="005";
	public static final String ERROR_CODE_FATAL="006";
	
	public static final String DEFAULT_PAGING_PROPS="paging.defaultpagesize";
	public static final Integer DEFAULT_PAGING=15;
	public static final Integer NO_PAGING=500;
	
	public static final String PAGINADO="Y";
	public static final String SORT_DIRECTION_ASC="asc";
	
	public static final String DATE_FORMAT_DATABASE="dd/MM/yyyy";
	
	public static final String NUMBER_AS_STRING_FORMAT="#,###,##0.00";
    public static final char NUMBER_AS_STRING_DECIMAL_SEPARATOR='.';
    public static final char NUMBER_AS_STRING_GROUP_SEPARATOR=',';
    
    public static final String FORMATO_FECHA_CORTA = "yyyy-MM-dd";
    public static final String FORMATO_FECHA_LARGA = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String FORMATO_FECHA_COMPLETA = "EEEE, d MMM yyyy HH:mm:ss";
    public static final String FORMATO_FECHA_MES_CENTURIA = "MM/yyyy";

	/**
	 * PARAMETROS WEB Y HTTPSESSION
	 */
	
	public static final String FILE_UPLOAD_SESSION_ATTRIB="fileUploadSession";
	public static final String USER_SESSION_ATTRIB="user";
	public static final String TOKEN_SESSION_ATTRIB="token";
	public static final String TEMPORAL_TOKEN_SESSION_ATTRIB="temporalToken";
	public static final String USER_NAME_SESSION_ATTRIB="userName";
	public static final String USER_IDENTITY_SESSION_ATTRIB="identificador";
	public static final String CARGO_SESSION_ATTRIB="cargo";
	public static final String ROL_SESSION_ATTRIB="rol";
	public static final String ROL_NAME_SESSION_ATTRIB="rolName";
	public static final String COMPANY_NAME_SESSION_ATTRIB="companyName";
	public static final String COMPANY_ID_SESSION_ATTRIB="companyId";
	public static final String COMPANY_SESSION_ATTRIB="company";
	public static final String COMPANY_IDENTIFICADOR_DEFAULT_SESSION_ATTRIB="9999999999001";
	public static final String PNG_FILE_EXTENSION=".png";
	public static final String PDF_FILE_EXTENSION=".pdf";
	public static final String XML_FILE_EXTENSION=".xml";
	public static final String PROPERTIES_FILE_EXTENSION=".properties";
	public static final String PDF_FILE_TYPE_EXTENSION="PDF";
	public static final String XML_FILE_TYPE_EXTENSION="XML";
	public static final String JASPER_FILE_EXTENSION=".jasper";
	
	
	
	public static final String EJB_SERVICE_SECURITY_PROP="ejb.service.security"; 
	public static final String FIRST_LOGIN_SECURITY_PROP="security.key.firstLogin";
	
	
	
	/**
	 * DATOS WEB TOKEN
	 */
	public static final String CRYPTO_JWT="segurossucre.2018";
	public static final String ISSUER_JWT="www.relative-engine.com";
	public static final long TTL_JWT=50000;
	public static final String KEYHEADER_JWT="Authorization";
	public static final String KEYHEADER_FIRST_LOGIN_JWT="Login";
	
	/**
	 * MENSAJES EXCEPCIONES, PARAMETROS JPS
	 */
	public static final String MSG_CON_ERROR = " con error ";
	public static final String MSG_ERROR_BUSQUEDA = "ERROR EN LA BUSQUEDA DE ";
	public static final String MSG_ERROR_EJECUCION = "ERROR EN EJECUCION DE ";
	public static final String REFRESH = "REFRESH";
	public static final String PERSISTENCE_CACHE_STOREMODE = "javax.persistence.cache.storeMode";
	public static final String ILLEGALSTATEEXCEPTION = "IllegalStateException ";
	public static final String QUERYTIMEOUTEXCEPTION = "QueryTimeoutException ";
	public static final String TRANSACTIONREQUIREDEXCEPTION = "TransactionRequiredException ";
	public static final String PESSIMISTICLOCKEXCEPTION = "PessimisticLockException ";
	public static final String PERSISTENCEEXCEPTION = "PersistenceException ";
	
	public static final String SECURITY_KEY_FIRST_LOGIN = "flsegurossucre.2018"; 
	public static final String SECURITY_SALT = "$2a$06$.rCVZVOThsIa97pEDOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC";
	
	/**
	 * MAIL CONSTANTS
	 */
	
	public static final String MAIL_SMTP ="smtp";
	public static final String MAIL_POP3 ="pop3s";
	public static final String MAIL_IMAP ="imaps";
	public static final String MAIL_INBOX ="INBOX";
	public static final String MAIL_SENT ="SENT";
	public static final String MAIL_OUTBOX ="OUTBOX";
	public static final String MAIL_MIME_TYPE_TEXT ="text/HTML"; 
	public static final String MAIL_MIME_TYPE_PNG ="image/png";
	public static final String MAIL_MIME_TYPE_PDF ="application/pdf";
	public static final String MAIL_MIME_TYPE_XLS ="application/xhtml+xml";
	public static final String MAIL_SUB_TYPE_HTML ="html";
	public static final String MAIL_DEFAULT_CHARSET ="UTF-8";
	public static final String MAIL_SMTP_HOST_PROPS ="mail.smtp.host";
	public static final String MAIL_SMTP_PORT_PROPS ="mail.smtp.port";
	public static final String MAIL_SMTP_AUTH_PROPS ="mail.smtp.auth";
	public static final String MAIL_SMTP_TLS_PROPS ="mail.smtp.starttls.enable";
	public static final String MAIL_SMTP_SOCKET_FACTORY_PORT_PROPS ="mail.smtp.socketFactory.port";
	public static final String MAIL_SMTP_SOCKET_FACTORY_CLASS_PROPS ="mail.smtp.socketFactory.class";
	public static final String MAIL_SMTP_SOCKET_FACTORY_CLASS_VALUE_PROPS ="javax.net.ssl.SSLSocketFactory";
	
	public static final String MAIL_POP3_HOST_PROPS ="mail.pop3.host";
	public static final String MAIL_POP3_PORT_PROPS ="mail.pop3.port";
	public static final String MAIL_POP3_AUTH_PROPS ="mail.pop3.auth";
	public static final String MAIL_POP3_TLS_PROPS ="mail.pop3.starttls.enable";
	public static final String MAIL_POP3_SOCKET_FACTORY_PORT_PROPS ="mail.pop3.socketFactory.port";
	public static final String MAIL_POP3_SOCKET_FACTORY_CLASS_PROPS ="mail.pop3.socketFactory.class";
	public static final String MAIL_POP3_SOCKET_FACTORY_CLASS_VALUE_PROPS ="javax.net.ssl.SSLSocketFactory";
	
	public static final String MAIL_IMAP_STORE_PROTOCOL_PROPS ="mail.store.protocol";
	public static final String MAIL_IMAP_PORT_PROPS ="mail.imap.port";
	public static final String MAIL_IMAP_AUTH_PROPS ="mail.imap.auth";
	public static final String MAIL_IMAP_TLS_PROPS ="mail.imap.starttls.enable";
	public static final String MAIL_IMAP_SOCKET_FACTORY_PORT_PROPS ="mail.imap.socketFactory.port";
	public static final String MAIL_IMAP_SOCKET_FACTORY_CLASS_PROPS ="mail.imap.socketFactory.class";
	public static final String MAIL_IMAP_SOCKET_FACTORY_CLASS_VALUE_PROPS ="javax.net.ssl.SSLSocketFactory";
	
	public static final String MAIL_MULTIPART_IMAGE ="<image>";

	private Constantes() {
		
	}
	
}
