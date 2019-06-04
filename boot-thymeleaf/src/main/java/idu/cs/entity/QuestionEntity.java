package idu.cs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "question")
public class QuestionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	// database에서 sequence number, primary key 역할
	@Column(nullable=false, length=20, unique=true)
	private String questionId;
	// ManyToOne때문에 에러나서 만든 필드, 수정시 삭제해야함.
	private String user;
	private String title;
	/*
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_user"))
	private String user;
	private String title;
	*/
	@Lob
	private String contents;
	private LocalDateTime createDate;
	
	// 생성자
	public QuestionEntity() {} // java에서 생성자 지정시 기본 생성자를 항상 생성해야 함.
	public QuestionEntity(String user, String title, String contents) {
		super();
		this.user = user;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
}