import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyCr23scvx4zGAGuHvBU2QMU5ThIeJbd_hI",
  authDomain: "music-storage-509a7.firebaseapp.com",
  projectId: "music-storage-509a7",
  storageBucket: "music-storage-509a7.appspot.com",
  messagingSenderId: "101882826578",
  appId: "1:101882826578:web:315c0e7dff80b8b3b8f48b",
};

export const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
