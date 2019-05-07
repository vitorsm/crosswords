package br.com.intcode.crosswords.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name="answer_history")
@IdClass(value = AnswerHistoryId.class)
@Entity
@Data
public class AnswerHistory {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name="question_id", nullable=false)
	private Question question;
	
	@Id
	@Column(name="answered_at", nullable=false)
	private Date answeredAt;
		
	@Column(name="correct", nullable=false)
	private boolean correct;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerHistory other = (AnswerHistory) obj;
		if (answeredAt == null) {
			if (other.answeredAt != null)
				return false;
		} else if (!answeredAt.equals(other.answeredAt))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answeredAt == null) ? 0 : answeredAt.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	
	
}
