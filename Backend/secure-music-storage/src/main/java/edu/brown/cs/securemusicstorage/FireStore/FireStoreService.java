package edu.brown.cs.securemusicstorage.FireStore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import edu.brown.cs.securemusicstorage.Entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Saves a data object to a specified Firestore collection.
     * The ID of the document is automatically generated.
     *
     * @param collection The name of the Firestore collection.
     * @param data The data object to be saved.
     * @param <T> The type of the data object.
     */
    public <T> void save(String collection, T data) {
        save(collection, data, generateId(collection));
    }

    /**
     * Saves a data object to a specified Firestore collection with a specified ID.
     *
     * @param collection The name of the Firestore collection.
     * @param data The data object to be saved.
     * @param id The ID of the document to be saved.
     * @param <T> The type of the data object.
     */
    public <T> void save(String collection, T data, String id) {
        firestore.collection(collection).document(id).set(data);
    }

    /**
     * Retrieves a data object from a specified Firestore collection by its ID.
     *
     * @param collection The name of the Firestore collection.
     * @param id The ID of the data object to be retrieved.
     * @param clazz The Class of the data object.
     * @param <T> The type of the data object.
     * @return The data object if found, null otherwise.
     */
    public <T> T get(String collection, String id, Class<T> clazz) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore.collection(collection).document(id).get();
            DocumentSnapshot document = future.get(); // This blocks on the document retrieval

            if (document.exists()) {
                return document.toObject(clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves all data objects from a specified Firestore collection.
     *
     * @param collection The name of the Firestore collection.
     * @param clazz The Class of the data object.
     * @param <T> The type of the data object.
     * @return A list of all data objects in the collection.
     */
    public <T> ArrayList<T> getAll(String collection, Class<T> clazz) {
        try {
            ApiFuture<QuerySnapshot> future = firestore.collection(collection).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            ArrayList<T> list = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                list.add(document.toObject(clazz));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generates a unique ID for a new document in a specified Firestore collection.
     *
     * @param collection The name of the Firestore collection.
     * @return The generated ID.
     */
    public String generateId(String collection) {
        return firestore.collection(collection).document().getId();
    }

}
