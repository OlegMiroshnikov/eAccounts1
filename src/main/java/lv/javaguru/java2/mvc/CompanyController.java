package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyRequest;
import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyResponse;
import lv.javaguru.java2.businesslogic.company.addcompany.AddCompanyService;
import lv.javaguru.java2.businesslogic.company.getcompany.GetCompanyService;
import lv.javaguru.java2.businesslogic.company.getcompanylist.GetCompanyListService;
import lv.javaguru.java2.businesslogic.company.removecompany.RemoveCompanyRequest;
import lv.javaguru.java2.businesslogic.company.removecompany.RemoveCompanyResponse;
import lv.javaguru.java2.businesslogic.company.removecompany.RemoveCompanyService;
import lv.javaguru.java2.businesslogic.company.updatecompany.UpdateCompanyRequest;
import lv.javaguru.java2.businesslogic.company.updatecompany.UpdateCompanyResponse;
import lv.javaguru.java2.businesslogic.company.updatecompany.UpdateCompanyService;
import lv.javaguru.java2.domain.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private AddCompanyService addCompanyService;
    @Autowired
    private UpdateCompanyService updateCompanyService;
    @Autowired
    private RemoveCompanyService removeCompanyService;
    @Autowired
    private GetCompanyService getCompanyService;
    @Autowired
    private GetCompanyListService getCompanyListService;

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public String listCompanies(ModelMap model) {
        model.addAttribute("company", new Company());
        model.addAttribute("listCompanies", this.getCompanyListService.getCompanyList());
        return "/companies";
    }

    @RequestMapping(value = "/company/add", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute("company") Company company) {
        if (company.getId() == null || company.getId() == 0) {
            AddCompanyRequest addCompanyRequest = new AddCompanyRequest(
                    company.getRegNr(),
                    company.getName(),
                    company.getAddress(),
                    company.geteMail(),
                    company.getPathFromAccounts(),
                    company.getPathToAccounts());
            AddCompanyResponse response = this.addCompanyService.addCompany(addCompanyRequest);
            if (response.isSuccess()) {
                logger.info("Company successfully saved. Company details: " + company);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }
        } else {
            UpdateCompanyRequest updateCompanyRequest = new UpdateCompanyRequest(
                    company.getId(),
                    company.getRegNr(),
                    company.getName(),
                    company.getAddress(),
                    company.geteMail(),
                    company.getPathFromAccounts(),
                    company.getPathToAccounts());
            UpdateCompanyResponse response = this.updateCompanyService.updateCompany(updateCompanyRequest);
            if (response.isSuccess()) {
                logger.info("Company successfully updated. Company details: " + company);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }
        }
        return "redirect:/companies";
    }

    @RequestMapping("/company/remove/{id}")
    public String removeCompany(@PathVariable("id") int id) {
        RemoveCompanyResponse response = this.removeCompanyService.removeCompany(new RemoveCompanyRequest(id));
        if (response.isSuccess()) {
            logger.info("Company successfully removed. Company id: " + id);
        } else {
            response.getErrors().forEach(error -> {
                logger.error(error.toString());
            });
        }
        return "redirect:/companies";
    }

    @RequestMapping("/company/edit/{id}")
    public String editCompany(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", this.getCompanyService.getCompanyById(id).get());
        model.addAttribute("listCompanies", this.getCompanyListService.getCompanyList());
        return "companies";
    }

    @RequestMapping("companydata/{id}")
    public String companyData(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", this.getCompanyService.getCompanyById(id).get());
        return "companydata";
    }
}