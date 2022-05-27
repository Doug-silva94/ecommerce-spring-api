package com.dev.api.springrest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;


@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long id;
    @Column(name = "cli_name")
    @NotNull(message = "Name may not be null")
    @Size(min = 10, max = 50)
    private String name;
    @Column(name = "cli_user", unique = true)
    @NotNull(message = "UserName may not be null")
    @Size(min = 5, max = 15)
    private String userName;
    @Column(name = "cli_email")
    @NotNull(message = "Email may not be null")
    @Size(min = 10, max = 50)
    private String email;
    @Column(name = "cli_cpf", unique = true)
    @NotBlank(message = "CPF may not be blank")
    @Size(max = 14)
    private String cpf;
    @Column(name = "cli_birth")
    private Date birthDate;
    @Column(name = "cli_address")
    @NotNull(message = "Address may not be null")
    @Size(min = 15, max = 50)
    private String address;
    @Column(name = "cli_tel")
    @NotBlank(message = "Telephone may not be blank")
    @Size(max = 15)
    private String telephone;

    @OneToOne(mappedBy = "client")
    private SaleTable saleTable;

    public Client() {
    }

    public Client(Long id, String name, String userName, String email, String cpf, Date birthDate, String address, String telephone) {
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
