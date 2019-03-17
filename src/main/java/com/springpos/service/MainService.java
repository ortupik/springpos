package com.springpos.service;

import com.springpos.bean.Contractor;
import com.springpos.bean.CustomerSite;
import com.springpos.bean.Sequence;
import com.springpos.bean.ServiceOrder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface MainService {

    public String institutionName();

    public String institutionMotto();

    public void setInstitution(Model institution);

    public void setInstitution(ModelAndView institution);

    public ModelAndView setLoggedIn(String loggedIn);

    public String getLoggedIn();

    public String getLoggedOut();

    public int getAccessPermition();

    public void setAccessPermition();

    public Model sessionLoggedIn(Model model);

    public String encryptStuff(String crap);
    
    public String decryptStuff(String crap);
    
    public ServiceOrder executeSequence();
    
    public Sequence getSequence();
    
     public void destroySequence();

    public Sequence pageTwo(CustomerSite customer);

    public Sequence pageThree(ServiceOrder order);

    public Sequence pageFour(Contractor contractor);

    public void clearPrevious();

    public void checkStaticOrder();

}
