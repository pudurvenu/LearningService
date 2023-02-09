package com.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.model.User;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(of = "uuid")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Builder.Default
	private String courseId = UUID.randomUUID().toString().replace("-","");
	
	private String title;
	
	private String description;
	
	private LocalDateTime createdDate;
	
	@OneToMany(targetEntity = DateOf.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ud_fk", referencedColumnName = "id")
	private List<DateOf> updatedDate;
	
	
	@OneToMany(targetEntity = FileResource.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fr_fk", referencedColumnName = "id")
	private List<FileResource> fileResources;
	
	
	@JsonBackReference	
	//@ToString.Exclude
    @ManyToOne //( cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE }) 
    //fetch = FetchType.EAGER,
    @JoinColumn(name = "instructor_id")
    private User instructor;
	
	
	@JsonBackReference(value = "course_user")
	@ManyToMany
	@JoinTable( name = "course_users", 
		   	joinColumns = @JoinColumn(name = "course_id"),
		   	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> subscriber;
		
	
	@OneToMany(targetEntity = Review.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "rw_fk", referencedColumnName = "id")
	private List<Review> reviews;
	
	private Integer enrollments;
	
	//private int price;
	
}
