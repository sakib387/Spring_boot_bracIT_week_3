package com.example.DynamicEmployeeManagementSystem.model;


import java.util.List;


public class AdditionalAttribute {
    private List<String>hobbies;
    private List<String>skill;

    public AdditionalAttribute() {
    }

    public AdditionalAttribute(List<String> hobbies, List<String> skill) {
        this.hobbies = hobbies;
        this.skill = skill;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }
}
