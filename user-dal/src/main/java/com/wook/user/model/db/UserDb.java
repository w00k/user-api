package com.wook.user.model.db;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_db")
public class UserDb {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<PhoneDb> phone = new ArrayList<PhoneDb>();
    
    @Column(name="create_date")
    private Date createDate;
    
    @Column(name="update_date")
	private Date updateDate;
    
    @Column(name="last_login")
	private Date lastLogin;
    
    @Column(name="token", nullable = false)
	private String token;
    
    @Column(name="is_active", nullable = false)
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneDb> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneDb> phone) {
		this.phone = phone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserDb() {
	}

	public UserDb(String name, String email, String password, List<PhoneDb> phone, Date createDate,
			Date updateDate, Date lastLogin, String token, boolean active) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.lastLogin = lastLogin;
		this.token = token;
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserDb [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", createDate=" + createDate + ", updateDate=" + updateDate + ", lastLogin=" + lastLogin
				+ ", token=" + token + ", active=" + active + "]";
	}
	
}
