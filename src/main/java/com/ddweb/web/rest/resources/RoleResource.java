package com.ddweb.web.rest.resources;

import com.ddweb.entities.Authorization;
import com.ddweb.entities.Role;
import com.ddweb.entities.UserGroup;
import com.ddweb.repositories.AuthorizationRepository;
import com.ddweb.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleResource {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleResource(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
            return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.ACCEPTED);
        else
            return ResponseEntity.status(401).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        //---------------uncomment--------------
//        if(id!=null) {
//            Role get = roleRepository.findOne(id);
//            if(get!=null) return new  ResponseEntity<>(get,HttpStatus.OK);
//            else return ResponseEntity.badRequest().build();
//        }else return ResponseEntity.badRequest().build();
        //---------------end-uncomment--------------
        //-------comment this later-------------
        Role role = new Role();
        if (id.equals(1L)) {
            role.setName("USER");
            role.setId(1L);
            role.setDescription("user desc");
        } else if (id.equals(2L)) {
            role.setName("ADMIN");
            role.setId(2L);
            role.setDescription("admin desc");
        } else if (id.equals(3L)) {
            role.setName("MOD");
            role.setId(3L);
            role.setDescription("mod desc");
        } else {
            role.setName("WRONG");
            role.setId(0L);
            role.setDescription("default desc");
        }
        return new  ResponseEntity<>(role,HttpStatus.OK);
        //-------end-comment this later-------------
    }

    @PutMapping
    public ResponseEntity<Role> updateRoleById(@RequestBody Role role){
        //---------------uncomment--------------
//        if(role!=null) {
//            roleRepository.save(role);
//            return ResponseEntity.ok().build();
//        }else return ResponseEntity.badRequest().build();
        //---------------end-uncomment--------------
        //-------comment this later-------------
        System.out.println(role.getName());
        return new ResponseEntity<>(role,HttpStatus.OK);
        //-------end-comment this later-------------
    }
//------------------------------------------ TAK TO ZROBIMY Z JHIPSTERA------------------------
//    @PutMapping("/bank-accounts")
//    @Timed
//    public ResponseEntity<BankAccount> updateBankAccount(@Valid @RequestBody BankAccount bankAccount) throws URISyntaxException {
//        log.debug("REST request to update BankAccount : {}", bankAccount);
//        if (bankAccount.getId() == null) {
//            return createBankAccount(bankAccount);
//        }
//        BankAccount result = bankAccountRepository.save(bankAccount);
//        return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankAccount.getId().toString()))
//                .body(result);
//    }
// ----------------------------------------------------------------------------------------


    //--------------------------tutaj paramem dac obiekt role a nie sama nazwa----------------------
    @DeleteMapping("/{roleParam}")
    public ResponseEntity deleteRole(@PathVariable("roleParam") String roleParam){
        if(roleParam!=null) {
            Role delete = roleRepository.findByName(roleParam);
            if(delete!=null){
                roleRepository.delete(delete);
            return ResponseEntity.ok().build();
            }else return ResponseEntity.badRequest().build();
        }else return ResponseEntity.badRequest().build();
    }
}
