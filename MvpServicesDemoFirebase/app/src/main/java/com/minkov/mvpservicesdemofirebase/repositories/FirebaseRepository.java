package com.minkov.mvpservicesdemofirebase.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.repositories.base.Repository;

import java.util.List;
import java.util.function.Consumer;

public class FirebaseRepository<T> implements Repository<T> {
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
        mDb.collection(mCollectionName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<T> objects = task.getResult().toObjects(mKlass);
                        accept.accept(objects);
                    }
                });
    }
}
