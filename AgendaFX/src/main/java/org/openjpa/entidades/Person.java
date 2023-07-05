package org.openjpa.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(catalog = "agendafmx", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Person.seleccionaTodos", query = "SELECT a FROM Person a"),
    @NamedQuery(name = "Person.seleccionaPorId", query = "SELECT a FROM Person a WHERE a.personId = :personId"),
    @NamedQuery(name = "Person.seleccionaPorApellidos", query = "SELECT a FROM Person a WHERE a.lastName = :lastName")})

public class Person implements Serializable
{
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "person_id", nullable = false)
        private Integer personId;
        @Column(length = 20)
	private String firstName;
        @Column(length = 40)
	private String lastName;
        @Column(length = 40)
	private String street;
        @Column
	private Integer postalCode;
        @Column(length = 10)
	private String city;
        @Temporal(TemporalType.TIMESTAMP)
	private LocalDate birthday;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null, null);
	}
	

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		// Some initial dummy data, just for convenient testing.
		this.street = "some street";
		this.postalCode = 1234;
		this.city = "some city";
		this.birthday = (LocalDate.of(1999, 2, 21));
	}

        public Integer getPersonId() {
            return personId;
        }

        public void setPersonId(Integer personId) {
            this.personId = personId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public Integer getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(Integer postalCode) {
            this.postalCode = postalCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }



        @Override
        public int hashCode() {
            int hash = 0;
            hash += (personId != null ? personId.hashCode() : 0);
            return hash;
        }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person [personId=" + personId + "]";
    }
}