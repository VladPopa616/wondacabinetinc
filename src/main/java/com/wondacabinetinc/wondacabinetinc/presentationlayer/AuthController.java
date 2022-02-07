package com.wondacabinetinc.wondacabinetinc.presentationlayer;

import com.wondacabinetinc.wondacabinetinc.businesslayer.EmployeeMapper;
import com.wondacabinetinc.wondacabinetinc.datalayer.*;
import com.wondacabinetinc.wondacabinetinc.jwt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Qualifier("passwordEncoder")
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenUtils tokenUtils;

    //TokenUtils tokenUtils = new TokenUtils();

    @Autowired
    EmployeeMapper employeeMapper;

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenUtils.generateToken(authentication);

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
        UserDetailsImpl userDetails = (UserDetailsImpl)  authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        log.info("Uid returned: " + userDetails.getId());
        log.info("User identified " + userDetails.getUsername());
        log.info("User email identified " + userDetails.getEmail());
        log.info("Password returned: " + userDetails.getPassword());
        log.info("Roles returned" + userDetails.getAuthorities());

        System.out.println(userDetails.getPassword());
        return ResponseEntity.ok(new JWTResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        if(employeeRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
        }

        if(employeeRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already in use"));
        }

        EmployeeDTO employee = new Employee(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPhone(), signUpRequest.getUsername(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role customerRole = roleRepository.findByRoleName(EnumRoles.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not Found"));
            roles.add(customerRole);

        }

        else{
            strRoles.forEach(role -> {
                if(role.equals("employee")){
                    Role employeeRole = roleRepository.findByRoleName(EnumRoles.ROLE_EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(employeeRole);
                }
                else{
                    Role customerRole = roleRepository.findByRoleName(EnumRoles.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not Found"));
                    roles.add(customerRole);
                }
            });
        }

        employee.setRoles(roles);
        employeeRepository.save(employeeMapper.employeeDTOtoEmployee(employee));
        return ResponseEntity.ok(new MessageResponse("User registered Successfully"));
    }

    @PostMapping("/loginnotoken")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> loginWithoutPassword(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = tokenUtils.generateToken(authentication);

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
        UserDetailsImpl userDetails = (UserDetailsImpl)  authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        log.info("Uid returned: " + userDetails.getId());
        log.info("User identified " + userDetails.getUsername());
        log.info("User email identified " + userDetails.getEmail());
        log.info("Password returned: " + userDetails.getPassword());
        log.info("Roles returned" + userDetails.getAuthorities());

        System.out.println(userDetails.getPassword());
        return ResponseEntity.ok(new NoJWTResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }
}
