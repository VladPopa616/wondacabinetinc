package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Employee;
import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mappings(
            {
                    @Mapping(target = "UId", ignore = true),
            }
    )
    Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    List<Employee> employeeDTOListToEmployeeList(List<EmployeeDTO> employeeDTOList);
    List<EmployeeDTO> employeeListToEmployeeDTOList(List<Employee> employeeList);
}
