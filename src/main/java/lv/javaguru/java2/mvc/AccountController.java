package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountRequest;
import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountResponse;
import lv.javaguru.java2.businesslogic.account.addaccount.AddAccountService;
import lv.javaguru.java2.businesslogic.account.getaccount.GetAccountService;
import lv.javaguru.java2.businesslogic.account.getaccountlist.GetAccountListService;
import lv.javaguru.java2.businesslogic.account.removeaccount.RemoveAccountRequest;
import lv.javaguru.java2.businesslogic.account.removeaccount.RemoveAccountResponse;
import lv.javaguru.java2.businesslogic.account.removeaccount.RemoveAccountService;
import lv.javaguru.java2.businesslogic.account.updateaccount.UpdateAccountRequest;
import lv.javaguru.java2.businesslogic.account.updateaccount.UpdateAccountResponse;
import lv.javaguru.java2.businesslogic.account.updateaccount.UpdateAccountService;
import lv.javaguru.java2.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private AddAccountService addAccountService;
    @Autowired
    private UpdateAccountService updateAccountService;
    @Autowired
    private RemoveAccountService removeAccountService;
    @Autowired
    private GetAccountService getAccountService;
    @Autowired
    private GetAccountListService getAccountListService;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String listAccounts(ModelMap model) {
        model.addAttribute("account", new Account());
        model.addAttribute("listAccounts", this.getAccountListService.getAccountList());
        return "/accounts";
    }

    @RequestMapping(value = "/account/add", method = RequestMethod.POST)
    public String addAccount(@ModelAttribute("account") Account account) {
        if (account.getId() == null || account.getId() == 0) {
            AddAccountRequest addAccountRequest = new AddAccountRequest(
                    account.getContractId(),
                    account.getFileName());
            AddAccountResponse response = this.addAccountService.addAccount(addAccountRequest);
            if (response.isSuccess()) {
                logger.info("Account successfully saved. Account details: " + account);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }

        } else {
            UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(
                    account.getId(),
                    account.getContractId(),
                    account.getFileName());
            UpdateAccountResponse response = this.updateAccountService.updateAccount(updateAccountRequest);
            if (response.isSuccess()) {
                logger.info("Account successfully updated. Account details: " + account);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }
        }
        return "redirect:/accounts";
    }

    @RequestMapping("/account/remove/{id}")
    public String removeAccount(@PathVariable("id") long id) {
        RemoveAccountResponse response = this.removeAccountService.removeAccount(new RemoveAccountRequest(id));
        if (response.isSuccess()) {
            logger.info("Account successfully removed. Account id: " + id);
        } else {
            response.getErrors().forEach(error -> {
                logger.error(error.toString());
            });
        }
        return "redirect:/accounts";
    }

    @RequestMapping("/account/edit/{id}")
    public String editAccount(@PathVariable("id") long id, Model model) {
        model.addAttribute("account", this.getAccountService.getAccountById(id).get());
        model.addAttribute("listAccounts", this.getAccountListService.getAccountList());
        return "accounts";
    }

    @RequestMapping("accountdata/{id}")
    public String accountData(@PathVariable("id") long id, Model model) {
        model.addAttribute("account", this.getAccountService.getAccountById(id).get());
        return "accountdata";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }
}
