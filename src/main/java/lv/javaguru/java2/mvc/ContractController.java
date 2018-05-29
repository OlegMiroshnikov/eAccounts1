package lv.javaguru.java2.mvc;

import lv.javaguru.java2.businesslogic.client.addclient.AddClientRequest;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientResponse;
import lv.javaguru.java2.businesslogic.client.addclient.AddClientService;
import lv.javaguru.java2.businesslogic.client.getclient.GetClientService;
import lv.javaguru.java2.businesslogic.client.getclientlist.GetClientListService;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientRequest;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientResponse;
import lv.javaguru.java2.businesslogic.client.removeclient.RemoveClientService;
import lv.javaguru.java2.businesslogic.client.updateclient.UpdateClientRequest;
import lv.javaguru.java2.businesslogic.client.updateclient.UpdateClientResponse;
import lv.javaguru.java2.businesslogic.client.updateclient.UpdateClientService;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractRequest;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractResponse;
import lv.javaguru.java2.businesslogic.contract.addcontract.AddContractService;
import lv.javaguru.java2.businesslogic.contract.getcontract.GetContractService;
import lv.javaguru.java2.businesslogic.contract.getcontractlist.GetContractListService;
import lv.javaguru.java2.businesslogic.contract.removecontract.RemoveContractRequest;
import lv.javaguru.java2.businesslogic.contract.removecontract.RemoveContractResponse;
import lv.javaguru.java2.businesslogic.contract.removecontract.RemoveContractService;
import lv.javaguru.java2.businesslogic.contract.updatecontract.UpdateContractRequest;
import lv.javaguru.java2.businesslogic.contract.updatecontract.UpdateContractResponse;
import lv.javaguru.java2.businesslogic.contract.updatecontract.UpdateContractService;
import lv.javaguru.java2.domain.Client;
import lv.javaguru.java2.domain.Contract;
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
import java.util.Map;

@Controller
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private AddContractService addContractService;
    @Autowired
    private UpdateContractService updateContractService;
    @Autowired
    private RemoveContractService removeContractService;
    @Autowired
    private GetContractService getContractService;
    @Autowired
    private GetContractListService getContractListService;

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String listContracts(ModelMap model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("listContracts", this.getContractListService.getContractList());
        return "/contracts";
    }

    @RequestMapping(value = "/contract/add", method = RequestMethod.POST)
    public String addContract(@ModelAttribute("contract") Contract contract) {
        if (contract.getId() == null || contract.getId() == 0) {
            AddContractRequest addContractRequest = new AddContractRequest(
                    contract.getCompanyId(),
                    contract.getClientId(),
                    contract.getNumber(),
                    contract.getDateSign(),
                    contract.getDateBegin(),
                    contract.getDateEnd(),
                    contract.getDayToSendAccount(),
                    contract.getCountDaysToSendReminder(),
                    contract.getStatus());
            AddContractResponse response = this.addContractService.addContract(addContractRequest);
            if (response.isSuccess()) {
                logger.info("Cobtract successfully saved. Contract details: " + contract);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }

        } else {
            UpdateContractRequest updateContractRequest = new UpdateContractRequest(
                    contract.getId(),
                    contract.getCompanyId(),
                    contract.getClientId(),
                    contract.getNumber(),
                    contract.getDateSign(),
                    contract.getDateBegin(),
                    contract.getDateEnd(),
                    contract.getDayToSendAccount(),
                    contract.getCountDaysToSendReminder(),
                    contract.getStatus());
            UpdateContractResponse response = this.updateContractService.updateContract(updateContractRequest);
            if (response.isSuccess()) {
                logger.info("Contract successfully updated. Contract details: " + contract);
            } else {
                response.getErrors().forEach(error -> {
                    logger.error(error.toString());
                });
            }
        }
        return "redirect:/contracts";
    }

    @RequestMapping("/contract/remove/{id}")
    public String removeContract(@PathVariable("id") int id) {
        RemoveContractResponse response = this.removeContractService.removeContract(new RemoveContractRequest(id));
        if (response.isSuccess()) {
            logger.info("Contract successfully removed. Contract id: " + id);
        } else {
            response.getErrors().forEach(error -> {
                logger.error(error.toString());
            });
        }
        return "redirect:/contracts";
    }

    @RequestMapping("/contract/edit/{id}")
    public String editContract(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", this.getContractService.getContractById(id).get());
        model.addAttribute("listContracts", this.getContractListService.getContractList());
        return "contracts";
    }

    @RequestMapping("contractdata/{id}")
    public String contractData(@PathVariable("id") int id, Model model) {
        model.addAttribute("contract", this.getContractService.getContractById(id).get());
        return "contractdata";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }
}

