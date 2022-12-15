package com.ticket.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="CONTACT")
public class Contact {
	
//	@Override
//	public String toString() {
//		return "Contact [cId=" + cId + ", name=" + name + ", secondName=" + secondName + ", work=" + work + ", email="
//				+ email + ", phone=" + phone + ", image=" + image + ", description=" + description + ", user=" + user
//				+ "]";
//	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	@Column(name="movieName")
	private String name;
	private String available_Seats;
	private String work;
//	@Column(name="ticketPrice")
	private String ticketPrice;
//	@Column(name="Timeslot")
	private String timeSlot;
	private String images;
	@Column(length = 5000)
	private String description;
	
	@ManyToOne	
	
	private User user;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWork() {
		return work;
	}
	public String getAvailable_Seats() {
		return available_Seats;
	}
	public void setAvailable_Seats(String available_Seats) {
		this.available_Seats = available_Seats;
	}
	public void setWork(String work) {
		this.work = work;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getImage() {
		return images;
	}
	public void setImage(String images) {
		this.images = images;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.cId==((Contact)obj).getcId();
	}
}
	
	
