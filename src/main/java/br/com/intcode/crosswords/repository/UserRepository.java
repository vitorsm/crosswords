package br.com.intcode.crosswords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.intcode.crosswords.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Transactional(readOnly = true)
	public User findByLogin(String login);
	
	@Transactional(readOnly = true)
	public User findByLoginAndPassword(String login, String password);
}
