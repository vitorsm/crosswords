package br.com.intcode.crosswords.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Table(name="question")
@Entity
@Data
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private int id;
	
	@Column(name="question_description", nullable=false)
	private String description;
	
	@Column(name="question_answer", nullable=false)
	private String answer;

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
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
		Question other = (Question) obj;
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
