package com.minkov.mvpservicesdemofirebase.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.models.base.EntityBase;
import com.minkov.mvpservicesdemofirebase.repositories.base.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FirebaseRepository<T extends EntityBase> implements Repository<T> {
    private final Class<T> mKlass;
    private final String mCollectionName;
    private final FirebaseFirestore mDb;

    public FirebaseRepository(Class<T> klass) {
        mKlass = klass;
        mCollectionName = klass.getSimpleName().toLowerCase() + "s";
        mDb = FirebaseFirestore.getInstance();
    }

    @Override
    public void getAll(Consumer<List<T>> accept) {
        Superhero x = new Superhero();
        mDb.collection(mCollectionName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<T> objects = StreamSupport.stream(task.getResult().spliterator(), false)
                                .map(doc -> {
                                    T object = doc.toObject(mKlass);
                                    object.setId(doc.getId());
                                    return object;
                                })
                                .collect(Collectors.toList());

                        accept.accept(objects);
                    }
                });
    }
}
