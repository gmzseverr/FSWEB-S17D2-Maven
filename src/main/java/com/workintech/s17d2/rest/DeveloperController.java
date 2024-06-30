package com.workintech.s17d2.rest;


import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    public Map<Integer, Developer> developers ;
    public Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable){
        this.taxable=taxable;
    }

    @PostConstruct
    public void init(){
        this.developers=new HashMap<>();
        this.developers.put(1,new Developer(1,"John",1000d,Experience.JUNIOR ));
    }

    //Tüm developerların listesi
    @GetMapping
    public List<Developer> getAllDevelopers(){
        return new ArrayList<>(developers.values());
    }

    //id mapte varsa döner

    @GetMapping("/{id} ")
    public Developer getDeveloperById(@PathVariable int id){
        return developers.get(id);
    }

    @PostMapping
    public Developer createDeveloper(@RequestBody Developer developer){
        double salary = developer.getSalary();


        if(developer.getExperience()==Experience.JUNIOR){
            salary -= taxable.getSimpleTaxRate();
            developer = new JuniorDeveloper(developer.getId(), developer.getName(),developer.getSalary(),developer.getExperience());
        } else if (developer.getExperience()==Experience.MID) {
            salary -= taxable.getMiddleTaxRate();
            developer = new MidDeveloper(developer.getId(), developer.getName(),developer.getSalary(),developer.getExperience());
        } else if(developer.getExperience()==Experience.SENIOR){
            salary -= taxable.getUpperTaxRate();
            developer = new SeniorDeveloper(developer.getId(), developer.getName(),developer.getSalary(),developer.getExperience());
        }
developers.put(developer.getId(),developer);
        return developer;
    }

    @PutMapping("/{id} ")
    public Developer updateDeveloper(@PathVariable int id, Developer developer){
        developers.put(id,developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        developers.remove(id);

    }













}
