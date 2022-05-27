package com.dev.api.springrest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String cpf;
    private Date birthDate;
    private String address;
    private String telephone;

    public ClientDTO() {}

    public ClientDTO(Long id, String name, String userName, String email, String cpf, Date birthDate, String address, String telephone) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
    }

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    
    

}
