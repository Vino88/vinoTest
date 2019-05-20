package com.vino.test.controllers.api.MasterData;

import com.vino.test.Prefix;
import com.vino.test.dto.EmployeeDto;
import com.vino.test.models.Employee;
import com.vino.test.service.EmployeeService;
import com.vino.test.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeControllerApi {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_EMPLOYEE)
    public Page<Employee> index(Pageable pageRequest, @RequestParam(name = "searchParam", required = false) String searchParam){
        Page<Employee> aIt = employeeService.findAll(pageRequest);
        if (searchParam != "") {
            aIt = employeeService.findAllWithParam(searchParam, pageRequest);
        }
        return aIt;
    }

    @PostMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_EMPLOYEE)
    public ResponseEntity<MessageUtil> save(@ModelAttribute EmployeeDto requestData){
        MessageUtil messageUtil;
        try {
            employeeService.addOrUpdateEmployee(requestData);
            messageUtil = new MessageUtil("success", "Data berhasil disimpan");
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal disimpan");
        }
        return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
    }


    @DeleteMapping(Prefix.PATH_API+Prefix.PATH_MASTER_DATA+Prefix.PATH_EMPLOYEE+"/delete/{id}")
    public ResponseEntity<MessageUtil> delete(@PathVariable String id){
        MessageUtil messageUtil;
        try {
            employeeService.delete(Integer.parseInt(id));
            messageUtil = new MessageUtil("success", "Data berhasil dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.OK);
        }catch (Exception ex){
            messageUtil = new MessageUtil("gagal", "Data gagal dihapus");
            return new ResponseEntity<MessageUtil>(messageUtil, HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
