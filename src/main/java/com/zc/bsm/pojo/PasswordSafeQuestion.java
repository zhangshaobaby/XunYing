package com.zc.bsm.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author 张少
 *密保问题类
 */
 @Entity
 @Table(name = "passwordSafeQuestion")
public class PasswordSafeQuestion {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	//主键
	String id;
	//问题
	String question;
	//密码
	String answer;
	@ManyToOne
	@JoinColumn(name = "userId")
	User user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
