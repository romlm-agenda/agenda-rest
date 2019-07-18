/**
 * @since 17 juil. 2019
 */
package org.agenda.data.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author LE MIERE Romain
 *
 */
@Document(collection = "users")
public class UserBean {

	private ObjectId id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private List<DayBean> days = new ArrayList<>();

	/**
	 * 
	 */
	public UserBean() {
		super();
	}

	public UserBean(ObjectId id, String email, String password, String firstName, String lastName, LocalDate birthDate,
			List<DayBean> days) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.days = days;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id.toHexString();
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(String id) {
		this.id = new ObjectId(id);
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public final LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public final void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the days
	 */
	public final List<DayBean> getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public final void setDays(List<DayBean> days) {
		this.days = days;
	}

}
