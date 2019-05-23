package com.vino.test.models;

import com.vino.test.dto.EmployeeDto;

import javax.persistence.*;

@Entity
@Table(name="tbl_employee")
public class Employee extends BaseModel<EmployeeDto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public void fromDto(EmployeeDto dto) {
        this.setId(dto.getId());
        this.setFullName(dto.getFullName());
        this.setEmail(dto.getEmail());
    }

    @Override
    public EmployeeDto toDto() {
        EmployeeDto dto = new EmployeeDto();

        dto.setId(this.getId());
        dto.setFullName(this.getFullName());
        dto.setEmail(this.getEmail());

        return dto;
    }
}
