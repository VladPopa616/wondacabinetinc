package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeDTO;
import javassist.NotFoundException;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployeesDTO();
    EmployeeDTO getEmployeeDTOById(long uid) throws NotFoundException;
}
