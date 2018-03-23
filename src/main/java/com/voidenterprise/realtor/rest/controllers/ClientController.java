package com.voidenterprise.realtor.rest.controllers;


import com.voidenterprise.realtor.models.Client;
import com.voidenterprise.realtor.models.Realtor;
import com.voidenterprise.realtor.rest.exception.ResourceConstraintViolationException;
import com.voidenterprise.realtor.rest.exception.ResourceNotFoundException;
import com.voidenterprise.realtor.rest.exception.RestBadReqeustException;
import com.voidenterprise.realtor.rest.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("v1")
public class ClientController {

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @Autowired
    ClientService clientService;


    @PostMapping("/clients")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
    @ResponseBody
    public ResponseEntity createClient(
            @Valid @RequestBody Client client) {
        try {
            log.info("inside realtor :: "+client.toString());
            client.setId(UUID.randomUUID());

            if(ifClientExists(client.getEmail(),client.getRealtorid())){
                throw new RestBadReqeustException("Client with this email already exists.");
            }

            Client newClient = clientService.addClient(client);
            log.info("newClient :: "+newClient.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        }
        catch (IllegalArgumentException | ResourceConstraintViolationException e) {
            throw new BadRequestException(e);
        }
        catch (NullPointerException  | ResourceNotFoundException e) {
            throw new InternalServerErrorException(e);
        }
    }


    @GetMapping("/realtor/{realtorId}/clients")
    @ResponseBody
    public List<Client> getClients(){
      List<Client> clients = clientService.getAllClients();
            return clients;
    }

    @GetMapping("/realtor/{realtorId}/clients/bday/today")
    @ResponseBody
    public List<Client> getClientsBday(
            @PathVariable("realtorId")  String realtorId
    ){
        List<Client> clients = clientService.getClientsWithBday(new Date(),realtorId);
        return clients;
    }

    Boolean ifClientExists(String email, UUID realtorId){
        return clientService.findByEmail(email,realtorId)!=null;
    }

}
