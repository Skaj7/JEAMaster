package domain;

import javax.inject.Named;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Kaj Suiker on 11-3-2017.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "User.followers", query = "SELECT u FROM User as u WHERE :user MEMBER OF u.following"),
    @NamedQuery(name = "User.email", query = "SELECT u FROM User as u WHERE u.email = :email"),
    @NamedQuery(name = "User.login", query = "SELECT u FROM User as u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "User.search", query = "SELECT u FROM  User as u WHERE u.username LIKE :username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String email;

    @ManyToMany
    private List<User> following;

    @OneToMany(mappedBy = "ownerHeart")
    private List<Heart> hearts;

    @OneToMany(mappedBy="ownerTweet")
    private List<Tweet> tweets;

    @Column(unique=true)
    private String username;

    private byte[] picture;
    private String bio;
    private Double lat;
    private Double lng;
    private String site;
    private String password;
    private int role;
    private Date createdAt;
    private Date updatedAt;

    public User() {
    }

    @PrePersist
    public void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(List<Heart> hearts) {
        this.hearts = hearts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
