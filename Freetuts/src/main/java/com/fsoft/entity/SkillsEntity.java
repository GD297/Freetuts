package com.fsoft.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skills")
public class SkillsEntity {


		@Id
		@Column(name = "id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
//		@Column(name = "usename")
//		private String username;
		
		@Column(name = "skills")
		private String skills;
		
//		@Column(name = "[skillPercent(%)]")
//		private Integer skillsPercent;
		
		@OneToMany(mappedBy = "skills",fetch = FetchType.LAZY)
		private List<SkillsAdmin>  skillsAdmin;
	
	
	
	
	
	
//	@Id
//	@Column(name = "id")
//	private String id;
//	
//	@Column(name = "java", columnDefinition = "varchar(4)")
//	private String java;
//	
//	@Column(name = "javaScript", columnDefinition = "varchar(4)")
//	private String javaScript;
//	
//	@Column(name = "frontEnd", columnDefinition = "varchar(4)")
//	private String frontEnd;
//	
//	@Column(name = "sqlServer", columnDefinition = "varchar(4)")
//	private String sqlServer;
//	
//	@Column(name = "mySql", columnDefinition = "varchar(4)")
//	private String mySql;
//	
//	@Column(name = "C_Cpp", columnDefinition = "varchar(4)")
//	private String C_Cpp;
//	
//	@Column(name = "C#.Net", columnDefinition = "varchar(4)")
//	private String Cs;
//	
//	@Column(name = "python", columnDefinition = "varchar(4)")
//	private String python;
//	
//	@Column(name = "nodeJS", columnDefinition = "varchar(4)")
//	private String nodeJS;
//	
//	@Column(name = "assembly", columnDefinition = "varchar(4)")
//	private String assembly;
//	
//	@Column(name = "english", columnDefinition = "varchar(4)")
//	private String english;
//	
		
//	@Column(name = "japanese", columnDefinition = "varchar(4)")
//	private String japanese;
//	
//		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//		 // Quan hệ n-n với đối tượng ở dưới (Admin) (1 địa điểm có nhiều người ở)
//		@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//		@ToString.Exclude // Khoonhg sử dụng trong toString()
//		@JoinTable(name = "skills_admin",//Tạo ra một join Table tên là ""
//					joinColumns = @JoinColumn(name = "skills_id"),  //TRong đó, khóa ngoại chính là skills_id trỏ tới class hiện tại (Skills)
//					inverseJoinColumns = @JoinColumn(name = "admin_username")//Khóa ngoại thứ 2 trỏ tới thuộc tính  (Admin)
//				)
//		private List<AdminEntity> admin;
	
}
