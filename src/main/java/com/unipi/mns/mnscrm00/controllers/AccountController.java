package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id){
       return accountService.getAccountById(id);
    }

    @PostMapping("/new")
    public Account createAccount(@RequestBody Account account){
        return accountService.insertAccount(account);
    }

    @GetMapping("/all")
    public List<Account> getAccounts(){
        return accountService.getAllAccounts();
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable String id, @RequestBody Account account){
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAccount(@PathVariable String id){
      return accountService.deleteAccountById(id);
    }

    /*
    @GetMapping("/speciality/{speciality}")
    public List<Doctor> getDoctorBySpecialty(@PathVariable String speciality){
        List<Doctor> doctorList = doctorRepository.findBySpeciality(speciality);

        if(doctorList.size() <= 0){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No doctors with this speciality found");
        }

        return doctorList;
    }

    @GetMapping("/{Id}/phones")
    public List<Phone> getDoctorPhones(@PathVariable String Id) {
        Optional<Doctor> doctor = doctorRepository.findById(Id);

        if (!doctor.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        if(doctor.get().getPhones().size() <= 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No phones found");
        }

        return doctor.get().getPhones();
    }

    @PostMapping("/{Id}/phones/new")
    public List<Phone> addDoctorPhone(@PathVariable String Id, @RequestBody Phone phone) {
        Optional<Doctor> doctor = doctorRepository.findById(Id);

        if (!doctor.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        doctor.get().getPhones().add(phone);
        doctorRepository.save(doctor.get());

        return doctor.get().getPhones();
    }

    @DeleteMapping("/{Id}/phones/{phoneId}")
    public boolean deleteDoctorPhone(@PathVariable String Id, @PathVariable String phoneId) {
        Optional<Doctor> doctor = doctorRepository.findById(Id);
        Optional<Phone> phone = phoneRepository.findById(phoneId);

        if (!doctor.isPresent() || !phone.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor or Phone not found");
        }

        if(!doctor.get().getPhones().contains(phone.get())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone for this Doctor was not found");
        }

        doctor.get().getPhones().remove(phone.get());
        doctorRepository.save(doctor.get());

        return true;
    }

     */
}
