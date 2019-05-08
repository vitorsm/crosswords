package br.com.intcode.crosswords.service;

import java.util.Iterator;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.models.User;

public abstract class ServiceServer<T, ID> {

	protected JpaRepository repository;
	
	/***
	 * A classe ServiceServerClass possui um atributo de repository
	 * Esse método serve para setá-lo com o repository deste service
	 * this.repository = [serviceRepository];
	 */
	public abstract void setRepository();
	
	public abstract void prepareToSave(T t, User user);
	public abstract void verifyToSave(T t, User currentAccount) throws PermissionException, DAOException;
	
	public abstract void insert(T t) throws BusinessRuleException, PermissionException, DAOException;
	public abstract void update(T t) throws BusinessRuleException, PermissionException, DAOException;
	public abstract void delete(T t) throws BusinessRuleException, PermissionException, DAOException;
	
	public void save(T t, User user) throws PermissionException, DAOException {
		if (this.repository == null)
			this.setRepository();
		
		verifyToSave(t, user);
		prepareToSave(t, user);
		
		try {
			repository.save(t);	
		} catch(Exception ex) {
			throw new DAOException(ex);
		}
		
	}
	
	public T findById(ID id) throws BusinessRuleException, PermissionException, DAOException {
		if (this.repository == null)
			this.setRepository();
		
		try {
			return repository.findById(id).isPresent() ? (T) repository.findById(id).get() : null;	
		} catch(Exception ex) {
			throw new DAOException(ex);
		}
		
	}
	
	public Iterator<T> findAll() throws DAOException {
		if (this.repository == null)
			this.setRepository();
		
		try {
			return (Iterator<T>) repository.findAll();
		} catch(Exception ex) {
			throw new DAOException(ex);
		}
	}
	
	public long count() throws DAOException {
		if (this.repository == null)
			this.setRepository();
		
		try {
			return repository.count();
		} catch(Exception ex) {
			throw new DAOException(ex);
		}
	}
	
	public boolean existsById(ID id) throws DAOException {
		if (this.repository == null)
			this.setRepository();
		
		try {
			return repository.existsById(id);
		} catch(Exception ex) {
			throw new DAOException(ex);
		}
	}
}
