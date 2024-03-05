package edu.brown.cs.securemusicstorage.Controller;

import edu.brown.cs.securemusicstorage.Entity.User;
import edu.brown.cs.securemusicstorage.FireStore.FireStoreService;
import edu.brown.cs.securemusicstorage.Response.ApiResponse;
import edu.brown.cs.securemusicstorage.Response.ResponseCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class testController {

    @Resource
    private FireStoreService fireStoreService;

    @PostMapping("/addUser")
    public ApiResponse addUser(@RequestBody User user) {
        ApiResponse response = new ApiResponse();
        try {
            fireStoreService.saveUser(user);
        } catch (Exception e) {
            response.fail(ResponseCode.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/getUser")
    public ApiResponse testGetUser(@RequestParam String id) {
        ApiResponse response = new ApiResponse();
        User user = fireStoreService.getUser(id);
        if (user != null) {
            response.setData(user);
            return response;
        }
        response.fail(ResponseCode.BAD_REQUEST);
        return response;
    }
}
