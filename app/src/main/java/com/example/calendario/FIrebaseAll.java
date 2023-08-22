package com.example.calendario;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class FIrebaseAll {


    public static void getUserDatos(String collection, ListenerRegistration registrationUserDatos, EventListener userDatosListeners) {
        DocumentReference documentReference = FirebaseFirestore.getInstance()
                .collection(collection)
                .document(FbUser.getCurrentUserId());
        registrationUserDatos = documentReference.addSnapshotListener(userDatosListeners);
    }
}
