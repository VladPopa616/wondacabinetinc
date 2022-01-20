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

    public Employee getEmployeeById(Integer id) throws NotFoundException{
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("No employee found for id: " + id));
    }

    @Override
    public EmployeeDTO getEmployeeDTOById(Integer id) throws NotFoundException {
        return employeeMapper.employeeToEmployeeDTO(getEmployeeById(id));
    }
}
