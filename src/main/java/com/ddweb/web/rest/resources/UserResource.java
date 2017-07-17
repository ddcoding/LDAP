package com.ddweb.web.rest.resources;

import com.ddweb.entities.ApplicationUser;
import com.ddweb.entities.Role;
import com.ddweb.repositories.ApplicationUserRepository;
import com.ddweb.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public UserResource(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationUser>> getAllRoles() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
            return new ResponseEntity<>(applicationUserRepository.findAll(), HttpStatus.ACCEPTED);
        else
            return ResponseEntity.status(401).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getRoleById(@PathVariable("id") Long id){
        //---------------uncomment--------------
//        if(id!=null) {
//            Role get = applicationUserRepository.findOne(id);
//            if(get!=null) return new  ResponseEntity<>(get,HttpStatus.OK);
//            else return ResponseEntity.badRequest().build();
//        }else return ResponseEntity.badRequest().build();
        //---------------end-uncomment--------------
        //-------comment this later-------------
        ApplicationUser applicationUser = new ApplicationUser();
        if (id.equals(1L)) {
            applicationUser.setName("jakdwo");
            applicationUser.setId(1L);
        } else if (id.equals(2L)) {
            applicationUser.setName("kardro");
            applicationUser.setId(2L);
        } else if (id.equals(3L)) {
            applicationUser.setName("bielelel");
            applicationUser.setId(3L);
        } else {
            applicationUser.setName("ktos");
            applicationUser.setId(0L);
        }
        return new  ResponseEntity<>(applicationUser,HttpStatus.OK);
        //-------end-comment this later-------------
    }

    @PutMapping
    public ResponseEntity<ApplicationUser> updateRoleById(@RequestBody ApplicationUser applicationUser){
        //---------------uncomment--------------
//        if(role!=null) {
//            roleRepository.save(role);
//            return ResponseEntity.ok().build();
//        }else return ResponseEntity.badRequest().build();
        //---------------end-uncomment--------------
        //-------comment this later-------------
        System.out.println(applicationUser.getName());
        return new ResponseEntity<>(applicationUser,HttpStatus.OK);
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


    //--------------------------tutaj paramem dac obiekt user a nie sama nazwa----------------------
//    @DeleteMapping("/{userParam}")
//    public ResponseEntity deleteRole(@PathVariable("userParam") String userParam){
//        if(userParam!=null) {
//            ApplicationUser delete = applicationUserRepository.findByName(userParam);
//            if(delete!=null){
//                applicationUserRepository.delete(delete);
//                return ResponseEntity.ok().build();
//            }else return ResponseEntity.badRequest().build();
//        }else return ResponseEntity.badRequest().build();
//    }
}
