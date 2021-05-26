package com.EcommerceApplications.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	@NotBlank(message="value required")
	@Size(min= 2,max=30,message="min 2 and max 30 are alloud!!")
	private String name;
	@Column(unique = true)
	@Email(regexp="^[A-Za-z0-9+_.-]+@(.+)$")
	private String email;
	
	private String password;
	@NotBlank(message="value required")
	@Size(min=10,message="enter correct mobile number")
	private String phone;
	@NotBlank(message="value required")
	@Size(min= 2,max=200,message="min 2 and max 200 are alloud!!")
	private String address;
	private String imageUrl;
	private String role;
	private boolean enabled;

	public User() {

	}



	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", address=" + address + ", imageUrl=" + imageUrl + ", role=" + role + ", enabled=" + enabled
				+ "]";
	}
	
	
	

}
