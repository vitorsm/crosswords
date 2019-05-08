package br.com.intcode.crosswords.models;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class AnswerHistoryId {
	private Integer question;
	private Integer user;
	private Date asnweredAt;
}
