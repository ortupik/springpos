package com.springpos.service.impl;

import com.springpos.bean.Contact;
import com.springpos.bean.Contractor;
import com.springpos.bean.CustomerSite;
import com.springpos.bean.Sequence;
import com.springpos.bean.ServiceOrder;
import com.springpos.bean.User;
import com.springpos.bean.Sessions;
import com.springpos.service.ContactService;
import com.springpos.service.ContractorService;
import com.springpos.service.CustomerSiteService;
import org.springframework.stereotype.Service;
import com.springpos.service.MainService;
import com.springpos.service.ServiceOrderService;
import com.springpos.service.SessionService;
import com.springpos.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MainServiceImpl implements MainService {

    private String session;

    private Sequence sequence;

    ModelAndView mv;

    String staticUser = "admin@gmail.com";

    String staticPass = "1234";

    private String loggedIn = "s";

    private int loggedOut = 0;

    private ServiceOrderService serviceOrderService;
    private CustomerSiteService customerSiteService;
    private ContactService contactService;
    private UserService userService;
    private SessionService sessionService;
    private ContractorService contractorService;

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public Sequence getSequence() {
        return this.sequence;
    }

    @Autowired
    public void setContractorService(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @Autowired
    public void setServiceOrderService(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    @Autowired
    public void setCustomerSiteService(CustomerSiteService customerSiteService) {
        this.customerSiteService = customerSiteService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String institutionName() {
        return "Computer Xperts";
    }

    @Override
    public String institutionMotto() {
        return "";
    }

    String getUser() {
        return loggedIn;
    }

    @Override
    public ModelAndView setLoggedIn(String loggedIn) {
        setLogout();
        Random rand = new Random();
        int rand_sess = rand.nextInt(1000);
        User usr = userService.findByEmail(staticUser);
        if (usr == null) {
            usr = new User();
            usr.setEmail(session);
            usr.setPassword(encryptStuff(staticPass));
            userService.save(usr);
        }
        User user = userService.findByEmail(loggedIn);
        Sessions sess = new Sessions();
        sess.setSessionName(user.getEmail() + " | " + String.valueOf(rand_sess));
        sess.setUserName(loggedIn);
        Sessions newSess = sessionService.save(sess);
        session = newSess.getSessionName() + newSess.getUserName();
        String sessName = newSess.getSessionName();
        mv = new ModelAndView();
        setLogin(session, loggedIn, rand_sess);
        mv.setViewName("adminPage");
        mv.addObject("sessionLoggedIn", sessName);
        mv.addObject("loggedIn", this.loggedIn);
        return mv;
    }

    @Override
    public String getLoggedIn() {
        return loggedIn;
    }

    @Override
    public Model sessionLoggedIn(Model model) {
        Sessions newSess = sessionService.findBySessionLatest();
        model.addAttribute("loggedIn", this.loggedIn);
        model.addAttribute("sessionLoggedIn", newSess.getSessionName());
        return model;
    }

    @Override
    public String getLoggedOut() {
        if (getLoggedIn() == null) {
            return "redirect:/";
        }

        return "redirect:/";
    }

    @Override
    public int getAccessPermition() {
        return loggedOut;
    }

    @Override
    public void setAccessPermition() {
        Random rand = new Random();
        loggedOut = rand.nextInt(1000);
    }

    @Override
    public String encryptStuff(String crap) {
        try {
            byte[] input = crap.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            byte[] cipherText = cipher.doFinal(input);
            String encodedString = Base64.getEncoder().encodeToString(cipherText);
            return encodedString;
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(MainServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    void setLogout() {
        session = null;
        this.loggedIn = null;
        loggedOut = 0;
        mv = null;
    }

    void setLogin(String sess, String loggedIn, int loggedOut) {
        this.session = sess;
        this.loggedIn = loggedIn;
        this.loggedOut = loggedOut;
    }

    @Override
    public void setInstitution(Model institution) {
        institution.addAttribute("institution", institutionName());
        institution.addAttribute("nstitle", institutionName());
        institution.addAttribute("motto", institutionMotto());
    }

    @Override
    public void setInstitution(ModelAndView institution) {
        institution.addObject("institution", institutionName());
        institution.addObject("nstitle", institutionName());
        institution.addObject("motto", institutionMotto());
    }

    @Override
    public Sequence pageTwo(CustomerSite customer) {
        if (this.sequence == null) {
            this.sequence = new Sequence();
        }
        if (customer != null) {
            this.sequence.setCustomer(customer);
        }
        return this.sequence;
    }

    @Override
    public Sequence pageThree(ServiceOrder order) {
        if (this.sequence == null) {
            return null;
        }
        if (order != null) {
            this.sequence.setServiceOrder(order);
        }
        return this.sequence;
    }

    @Override
    public Sequence pageFour(Contractor contractor) {
        if (this.sequence == null) {
            return null;
        }
        if (contractor != null) {
            this.sequence.setContractor(contractor);
        }
        return this.sequence;
    }

    @Override
    public void destroySequence() {
        this.sequence = null;
    }

    @Override
    public ServiceOrder executeSequence() {
        CustomerSite customer = this.customerSiteService.save(this.sequence.getCustomer());
        if (this.customerSiteService.findByCust_site_name(this.sequence.getCustomer().getCustSiteName()) == null) {
            customer = this.customerSiteService.save(this.sequence.getCustomer());
            Contact cont = new Contact(customer);
            this.contactService.save(cont);
        }
        Contractor contractor = this.sequence.getContractor();
        ServiceOrder order = this.sequence.getServiceOrder();
        if (customer == null ||contractor ==null|| order == null) {
        return null;
        }
        ServiceOrder newOrder=this.serviceOrderService.save(order);
        return newOrder;
    }

}
