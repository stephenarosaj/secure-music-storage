package edu.brown.cs.securemusicstorage.FireStore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FireStoreConfig {

    private String JSON_PATH = "music-group-27fc7-firebase-adminsdk-4gy3e-ae768e79cf.json";
    @Bean
    public Firestore firestore() throws IOException {
        // Initialize the Firebase app with credentials and get Firestore instance
        GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource(JSON_PATH).getInputStream());
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();

        // check if app already initialized to avoid exception
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
        return FirestoreClient.getFirestore();
    }
}
