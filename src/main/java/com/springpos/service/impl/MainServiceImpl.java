package com.springpos.service.impl;

import com.springpos.bean.Assignment;
import com.springpos.bean.Contractor;
import com.springpos.bean.Country;
import com.springpos.bean.CustomerSite;
import com.springpos.bean.Sequence;
import com.springpos.bean.ServiceOrder;
import com.springpos.bean.User;
import com.springpos.bean.Sessions;
import com.springpos.bean.State;
import com.springpos.service.AssignmentService;
import com.springpos.service.CountryService;
import com.springpos.service.CustomerSiteService;
import org.springframework.stereotype.Service;
import com.springpos.service.MainService;
import com.springpos.service.ServiceOrderService;
import com.springpos.service.SessionService;
import com.springpos.service.StateService;
import com.springpos.service.UserService;
import java.util.Random;
import org.jasypt.util.text.BasicTextEncryptor;
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

    private String loggedIn = "";

    private int loggedOut = 0;

    String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    private ServiceOrderService serviceOrderService;
    private CustomerSiteService customerSiteService;
    private StateService stateService;
    private UserService userService;
    private SessionService sessionService;
    private CountryService countryService;
    private AssignmentService assignmentService;

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public Sequence getSequence() {
        return this.sequence;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
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
    public void setAssignmentService(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
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
        for (String s : states) {
            if (this.stateService.findState(s) == null) {
                State state = new State();
                state.setStateName(s);
                this.stateService.save(state);
            }
        }
        if (this.countryService.findCountry("United States") != null) {
            Country country = new Country();
            country.setCountryName("United States");
            this.countryService.save(country);
        }
        setLogout();
        Random rand = new Random();
        int rand_sess = rand.nextInt(1000);
        User user = userService.findByEmail(loggedIn);
        Sessions sess = new Sessions();
        sess.setSessionName(user.getEmail() + " | " + String.valueOf(rand_sess));
        sess.setUserName(loggedIn);
        Sessions newSess = sessionService.save(sess);
        session = newSess.getSessionName() + newSess.getUserName();
        String sessName = newSess.getSessionName();
        mv = new ModelAndView();
        setLogin(session, loggedIn, rand_sess);
        mv.setViewName("redirect:/adminPage");
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
        setLogout();
        destroySequence();
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
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        String privateData = crap;
        textEncryptor.setPasswordCharArray("nospaniol".toCharArray());
        String ecryptedCrap = textEncryptor.encrypt(privateData);
        return ecryptedCrap;
    }

    @Override
    public String decryptStuff(String crap) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray("nospaniol".toCharArray());
        String decryptedCrap = textEncryptor.decrypt(crap);
        return decryptedCrap;
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
        this.sequence = new Sequence();
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
        CustomerSite customer = this.sequence.getCustomer();
        if (customer == null) {
            return null;
        }
        if (this.customerSiteService.findByCust_site_name(customer.getCustSiteName()) == null) {
            customer = this.customerSiteService.save(this.sequence.getCustomer());
        } else {
            customer = this.customerSiteService.findByCust_site_name(customer.getCustSiteName());
        }
        Contractor contractor = this.sequence.getContractor();
        ServiceOrder order = this.sequence.getServiceOrder();
        if (customer == null || contractor == null || order == null) {
            return null;
        }
        this.sequence.getServiceOrder().setCustomerSite(customer);
        this.sequence.getServiceOrder().setContractor(contractor);
        ServiceOrder newOrder = this.serviceOrderService.save(order);
        Assignment ass = new Assignment();
        ass.setContractor(contractor);
        ass.setServiceOrder(newOrder);
        this.assignmentService.save(ass);
        clearPrevious();
        return newOrder;
    }

    @Override
    public void clearPrevious() {
        sequence = null;
    }

    @Override
    public void checkStaticOrder() {
        User usr = userService.findByEmail(staticUser);
        if (usr == null) {
            usr = new User();
            usr.setEmail(staticUser);
            usr.setPassword(encryptStuff(staticPass));
            userService.save(usr);
        }
    }

}
