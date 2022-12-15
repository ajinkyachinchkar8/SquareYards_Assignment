package com.ticket.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticket.dao.ContactRepository;
import com.ticket.dao.UserRepository;
import com.ticket.entities.User;
import com.ticket.entities.Contact;
import com.ticket.helper.Message;
import java.util.Optional;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;


@ModelAttribute
public void addCommonData(Model model, Principal principal) {
	String userName = principal.getName();
	System.out.println("USERNAME " + userName);

	// get the user using usernamne(Email)

	User user = userRepository.getUserByUserName(userName);
	System.out.println("USER " + user);
	model.addAttribute("user", user);

}

// dashboard home
@RequestMapping("/index")
public String dashboard(Model model, Principal principal) {
	model.addAttribute("title", "User Dashboard");
	return "normal/user_dashboard";
}

// open add form handler
@GetMapping("/add-contact")
public String openAddContactForm(Model model) {
	model.addAttribute("title", "Book Ticket");
	model.addAttribute("contact", new Contact());

	return "normal/add_contact_form";
}

@PostMapping("/process-contact")
public String processaddContactform(@ModelAttribute Contact contact,Principal principal,HttpSession session) {
	
	String name = principal.getName();
	User user = this.userRepository.getUserByUserName(name);

	user.getContacts().add(contact);

	contact.setUser(user);

	this.userRepository.save(user);

	System.out.println("contact added");
	session.setAttribute("message", new Message("Your contact is added !! Add more..", "success"));

	return "normal/add_contact_form";
}

@GetMapping("/show-contacts")
public String showcontacts(Model m,Principal principal)
{
	m.addAttribute("title", "Show Booked Tickets");
	
	String userName = principal.getName();

	User user = this.userRepository.getUserByUserName(userName);
	
	List<Contact> contacts=this.contactRepository.findContactsByUser(user.getId());
	m.addAttribute("contacts",contacts);
	return "normal/show_contacts";
}


@GetMapping("/delete/{cid}")
@Transactional
public String deleteContact(@PathVariable("cid") Integer cId, Model model, HttpSession session,
		Principal principal) {
	System.out.println("CID " + cId);

	Contact contact = this.contactRepository.findById(cId).get();
	

	User user = this.userRepository.getUserByUserName(principal.getName());

	user.getContacts().remove(contact);

	this.userRepository.save(user);

	System.out.println("DELETED");
	

	return "redirect:/user/show-contacts";
}

@PostMapping("/update-contact/{cid}")
public String updateForm(@PathVariable("cid") Integer cid, Model m) {

	m.addAttribute("title", "Update Booked Tickets");

	Contact contact = this.contactRepository.findById(cid).get();

	m.addAttribute("contact", contact);

	return "normal/update_form";
}

//update contact handler
	@RequestMapping(value = "/process-update", method = RequestMethod.POST)
	public String updateHandler(@ModelAttribute Contact contact,
			Model m, HttpSession session, Principal principal) {



			User user = this.userRepository.getUserByUserName(principal.getName());

			contact.setUser(user);

			this.contactRepository.save(contact);


			
			return "redirect:/user/show-contacts";
	}
	
	
	@GetMapping("/profile")
	public String yourProfile(Model model)
	{
		model.addAttribute("title","Profile Page");
		return "normal/profile";
	}



}