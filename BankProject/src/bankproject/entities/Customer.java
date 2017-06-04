package bankproject.entities;

public class Customer extends AbstractEntity {

	/********************************
	 ********** Attributes **********
	 ********************************/

	private Integer id;
	private String firstname;
	private String lastname;

	/******************************
	 ********** Getters ***********
	 ******************************/

	public Integer getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFullName() {
		return firstname + " " + lastname;
	}

	/******************************
	 ********** Setters ***********
	 ******************************/

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


}