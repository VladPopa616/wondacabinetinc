package com.wondacabinetinc.wondacabinetinc.presentationlayer;

import com.wondacabinetinc.wondacabinetinc.businesslayer.EmployeeService;
import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeDTO;
import javassist.NotFoundException;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class EmployeeResource {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    @CrossOrigin
    @ResponseBody
    //@PreAuthorize("hasRole('EMPLOYEE')")
    public List<EmployeeDTO> getAllEmployeesDTO(){
        return employeeService.getAllEmployeesDTO();
    }

    @GetMapping("/{id}")
    @CrossOrigin
    @ResponseBody
   // @PreAuthorize("hasRole('EMPLOYEE')")
    public EmployeeDTO findEmployee(@PathVariable Integer id) throws NotFoundException {
        return employeeService.getEmployeeDTOById(id);
    }

    @GetMapping("/password")
    @CrossOrigin
    @ResponseBody
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String generatePassword(){
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule upper = new CharacterRule(EnglishCharacterData.UpperCase);
        CharacterRule lower = new CharacterRule(EnglishCharacterData.LowerCase);
        CharacterRule specialCharacterRule = new CharacterRule(new CharacterData() {
            @Override
            public String getErrorCode() {
                return "SAMPLE_ERROR_CODE";
            }

            @Override
            public String getCharacters() {
                return "!@#_-";
            }
        });

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generatePassword(8,digits, upper, lower, specialCharacterRule);
        return password;
    }
}
