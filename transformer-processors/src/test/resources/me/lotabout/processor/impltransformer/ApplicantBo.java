package me.lotabout.bo;

import me.lotabout.annotation.Transformer;
import me.lotabout.pojo.ApplicantPojo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Transformer(to = {ApplicantPojo.class})
public class ApplicantBo {
    private int id;
    private String name;
    private int age;
    private Integer salary;
    private List<EducationVo> educationList;
    private List<List<String>> additionalInfo;
    private Map<String, EducationVo> educationVoMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public List<EducationVo> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<EducationVo> educationList) {
        this.educationList = educationList;
    }

    public List<List<String>> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<List<String>> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Map<String, EducationVo> getEducationVoMap() {
        return educationVoMap;
    }

    public void setEducationVoMap(Map<String, EducationVo> educationVoMap) {
        this.educationVoMap = educationVoMap;
    }
}
