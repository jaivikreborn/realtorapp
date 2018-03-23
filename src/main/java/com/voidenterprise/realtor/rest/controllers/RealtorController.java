package com.voidenterprise.realtor.rest.controllers;


import com.voidenterprise.realtor.models.Realtor;
import com.voidenterprise.realtor.models.request.RealtorRequest;
import com.voidenterprise.realtor.rest.exception.ResourceConstraintViolationException;
import com.voidenterprise.realtor.rest.exception.ResourceNotFoundException;
import com.voidenterprise.realtor.rest.exception.RestBadReqeustException;
import com.voidenterprise.realtor.rest.services.RealtorService;
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
import javax.ws.rs.core.Context;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("v1")
public class RealtorController {

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    @Autowired
    RealtorService realtorService;


    @PostMapping("/realtor")
    @Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
    @ResponseBody
    public ResponseEntity eFileReturn(
            @Valid @RequestBody  RealtorRequest realtorRequest) {
        try {
            log.info("inside realtor :: "+realtorRequest.toString());
            Realtor realtor = new Realtor(realtorRequest);

            if(ifRealtorExists(realtor.getEmail())){
                throw new RestBadReqeustException("Realtor with this email already exists.");
            }

            Realtor newRealtor = realtorService.addRealtor(realtor);
            log.info("newRealtor :: "+newRealtor.toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(newRealtor);
        }
        catch (IllegalArgumentException | ResourceConstraintViolationException e) {
            throw new BadRequestException(e);
        }
        catch (NullPointerException  | ResourceNotFoundException e) {
            throw new InternalServerErrorException(e);
        }
    }


    @GetMapping("/realtor")
    @ResponseBody
    public List<Realtor> getUsers(){
      List<Realtor> realtors = realtorService.getAllRealtors();
            return realtors;
    }

    Boolean ifRealtorExists(String Email){
        return realtorService.findByEmail(Email)!=null;
    }

}
