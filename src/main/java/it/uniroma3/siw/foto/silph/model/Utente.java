package it.uniroma3.siw.foto.silph.model;

import javax.persistence.*;

/**
 * A Utente is a generic person who can use our application.
 * Subclasses of Utente include Admin and Customer.
 * @see Utente
 */
@Entity
@Table(name = "utenti")
public class Utente {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    protected Long id;

    /**
     * The first name of this Utente
     */
    @Column(name = "first_name")
    protected String firstName;

    /**
     * The last name of this Utente
     */
    @Column(name = "last_name")
    protected String lastName;

    /**
     * The username of this Utente for authentication
     */
    @Column(name = "username", unique=true)
    protected String username;

    /**
     * The password of this Utente for authentication
     */
    @Column(name = "password")
    protected String password;

    /**
     * The authorization role for this Utente
     */
    @Column(name = "role")
    protected String role;

    /**
     * Constructor
     *
     * @param id The id of this Utente
     * @param firstName The first name of this Utente
     * @param lastName The last name of this Utente
     * @param username The username of this Utente for authentication
     * @param password The password of this Utente for authentication
     * @param role The authorization role for this Utente
     */
    public Utente(Long id, String firstName, String lastName, String username, String password, String role) {
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
    public Utente() {
    }

    /**
     * Id getter
     * @return the Id for this Utente
     */
    public Long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id the id for this Utente
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * First name getter
     * @return the first name for this Utente
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * first name setter
     * @param firstName the first name for this Utente
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name getter
     * @return the last name for this Utente, as a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Last name setter
     * @param lastName the last name for this Utente
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * username getter
     * @return the username for this Utente
     */
    public String getUsername() {
        return username;
    }

    /**
     * username setter
     * @param username the username for this Utente
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * password getter
     * @return the password for this Utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * password setter
     * @param password the password for this Utente
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * role getter
     * @return role the role for this Utente
     */
    public String getRole() {
        return role;
    }

    /**
     * role setter
     * @param role the role for this Utente
     */
    public void setRole(String role) {
        this.role = role;
    }
}
