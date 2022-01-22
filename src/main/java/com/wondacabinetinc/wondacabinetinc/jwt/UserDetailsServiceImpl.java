package com.wondacabinetinc.wondacabinetinc.jwt;

import com.wondacabinetinc.wondacabinetinc.businesslayer.EmployeeMapper;
import com.wondacabinetinc.wondacabinetinc.datalayer.Employee;
import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeDTO;
import com.wondacabinetinc.wondacabinetinc.datalayer.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    EmployeeRepository employeeRepository;

    EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeDTO employee = employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(employee);
    }
}
