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
//		 // Quan h??? n-n v???i ?????i t?????ng ??? d?????i (Admin) (1 ?????a ??i???m c?? nhi???u ng?????i ???)
//		@EqualsAndHashCode.Exclude // kh??ng s??? d???ng tr?????ng n??y trong equals v?? hashcode
//		@ToString.Exclude // Khoonhg s??? d???ng trong toString()
//		@JoinTable(name = "skills_admin",//T???o ra m???t join Table t??n l?? ""
//					joinColumns = @JoinColumn(name = "skills_id"),  //TRong ????, kh??a ngo???i ch??nh l?? skills_id tr??? t???i class hi???n t???i (Skills)
//					inverseJoinColumns = @JoinColumn(name = "admin_username")//Kh??a ngo???i th??? 2 tr??? t???i thu???c t??nh  (Admin)
//				)
//		private List<AdminEntity> admin;
	
}
