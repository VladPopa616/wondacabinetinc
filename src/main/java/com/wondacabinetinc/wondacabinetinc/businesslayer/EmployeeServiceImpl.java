package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Employee;
import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeDTO;
import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesDTO() {
        return employeeMapper.employeeListToEmployeeDTOList(getAllEmployees());
    }

    public Employee getEmployeeByUid(long uid) throws NotFoundException{
        return employeeRepository.findByUid(uid).orElseThrow(() -> new NotFoundException("No user was found for id " + uid));
    }

    @Override
    public EmployeeDTO getEmployeeDTOById(long uid) throws NotFoundException {
        return employeeMapper.employeeToEmployeeDTO(getEmployeeByUid(uid));
    }

    @Override
    public String updatePassword(String email, String password) throws NotFoundException{
        try{
            Employee foundEmployee = employeeRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("No user was found for email " + email));

            foundEmployee.setPassword(password);

            employeeRepository.save(foundEmployee);
            return "Password updated";
        }
        catch(Exception e){
            return "Password update failed: " + e.getMessage();
        }


    }
}
