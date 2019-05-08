package br.com.intcode.crosswords.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Table(name="user")
@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Column(name="user_login", nullable=false, unique=true)
	private String login;
	
	@Column(name="user_name", nullable=false)
	private String name;
	
	@Column(name="user_password", nullable=false)
	private String password;

	@OneToMany(mappedBy = "user",
			fetch = FetchType.LAZY,
			orphanRemoval = true,
			cascade = javax.persistence.CascadeType.ALL)
	private List<AnswerHistory> answerHistory;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User createdBy;
	
	@Column(name = "created_at",
			nullable = false,
			columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	
}
