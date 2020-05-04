package ec.com.def.core.util.main;

import java.io.Serializable;

import ec.com.def.core.util.enums.LanguageEnum;

/**
 * Clase usuario utilizada para la gestion de seguridades y JWT
 * 
 * @author LUIS TAMAYO - RELATIVE ENGINE
 *
 */
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6702611587192856957L;
	private String nombre;
	private String id;
	private String role;
	private String password;
	private String company;
	private String token;
	private LanguageEnum language;
	private boolean loggedIn;
	private String type;
	private String email;
	private Boolean blockedIn;

	private byte[] userIconEmpty;

	/**
	 * Constructur
	 * 
	 * @param nombre
	 * @param id
	 * @param role
	 * @param password
	 * @param company
	 * @param token
	 * @param language
	 * @param loggedIn
	 * @param type
	 */
	public Usuario(String nombre, String id, String role, String password, String company, String token,
			LanguageEnum language, boolean loggedIn, String type) {
		this.nombre = nombre;
		this.id = id;
		this.role = role;
		this.password = password;
		this.company = company;
		this.token = token;
		this.language = language;
		this.loggedIn = loggedIn;
		this.type = type;

	}

	/**
	 * Constructur default
	 */
	public Usuario() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LanguageEnum getLanguage() {
		return language;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getUserIconEmpty() {
		return userIconEmpty;
	}

	public void setUserIconEmpty(byte[] userIconEmpty) {
		this.userIconEmpty = userIconEmpty;
	}

	public Boolean getBlockedIn() {
		return blockedIn;
	}

	public void setBlockedIn(Boolean blockedIn) {
		this.blockedIn = blockedIn;
	}

}
