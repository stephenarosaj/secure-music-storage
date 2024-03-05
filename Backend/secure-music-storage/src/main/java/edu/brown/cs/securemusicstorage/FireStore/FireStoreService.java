package edu.brown.cs.securemusicstorage.FireStore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import edu.brown.cs.securemusicstorage.Entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FireStoreService {

    @Resource
    private Firestore firestore;

    public void saveUser(User user) {
        firestore.collection("users").document(user.getId()).set(user);
    }

    public User getUser(String id) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore.collection("users").document(id).get();
            DocumentSnapshot document = future.get(); // This blocks on the document retrieval

            if (document.exists()) {
                return document.toObject(User.class);
            } else {
                System.out.println("No such user found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
