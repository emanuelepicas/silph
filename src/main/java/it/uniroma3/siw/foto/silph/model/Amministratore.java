package it.uniroma3.siw.foto.silph.model;

import javax.persistence.*;

/**
 * A Amministratore is a generic person who can use our application.
 * Subclasses of Amministratore include Admin and Customer.
 * @see Amministratore
 */
@Entity
@Table(name = "utenti")
public class Amministratore {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    protected Long id;

    /**
     * The first name of this Amministratore
     */
    @Column(name = "first_name")
    protected String firstName;

    /**
     * The last name of this Amministratore
     */
    @Column(name = "last_name")
    protected String lastName;

    /**
     * The username of this Amministratore for authentication
     */
    @Column(name = "username", unique=true)
    protected String username;

    /**
     * The password of this Amministratore for authentication
     */
    @Column(name = "password")
    protected String password;

    /**
     * The authorization role for this Amministratore
     */
    @Column(name = "role")
    protected String role;

    /**
     * Constructor
     *
     * @param id The id of this Amministratore
     * @param firstName The first name of this Amministratore
     * @param lastName The last name of this Amministratore
     * @param username The username of this Amministratore for authentication
     * @param password The password of this Amministratore for authentication
     * @param role The authorization role for this Amministratore
     */
    public Amministratore(Long id, String firstName, String lastName, String username, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Empty Constructor
     */
    public Amministratore() {
    }

    /**
     * Id getter
     * @return the Id for this Amministratore
     */
    public Long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id the id for this Amministratore
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * First name getter
     * @return the first name for this Amministratore
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * first name setter
     * @param firstName the first name for this Amministratore
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name getter
     * @return the last name for this Amministratore, as a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Last name setter
     * @param lastName the last name for this Amministratore
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * username getter
     * @return the username for this Amministratore
     */
    public String getUsername() {
        return username;
    }

    /**
     * username setter
     * @param username the username for this Amministratore
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * password getter
     * @return the password for this Amministratore
     */
    public String getPassword() {
        return password;
    }

    /**
     * password setter
     * @param password the password for this Amministratore
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * role getter
     * @return role the role for this Amministratore
     */
    public String getRole() {
        return role;
    }

    /**
     * role setter
     * @param role the role for this Amministratore
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Amministratore other = (Amministratore) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Amministratore [id = " + id + ", nome = " + firstName + ", cognome = " + lastName + "]";
    }
}
