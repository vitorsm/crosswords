package br.com.intcode.crosswords.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.intcode.crosswords.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	@Query("SELECT question FROM Question question WHERE NOT IN ("
			+ "SELECT answerHistory.question FROM AnswerHistory answerHistory"
			+ ")")
	@Transactional(readOnly=true)
	public List<Question> findAllNotAnswered();
	
}
