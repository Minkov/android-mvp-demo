package com.minkov.mvpdemo.repositories;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.minkov.mvpdemo.repositories.base.Repository;

import java.util.List;
import java.util.function.Consumer;

public class FirebaseRepository<T> implements Repository<T> {

    private final Class<T> mKlass;
    private final FirebaseFirestore mDb;
    private final String mCollectionName;

    public FirebaseRepository(Class<T> klass) {
        mKlass = klass;
        mCollectionName = klass.getSimpleName().toLowerCase() + "s";
        mDb = FirebaseFirestore.getInstance();
    }

    @Override
    public void getAll(Consumer<List<T>> onComplete) {
        getCollection()
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<T> entities = task.getResult().toObjects(mKlass);
                    onComplete.accept(entities);
                }
            });
    }

    @Override
    public void add(T entity, Consumer<T> onComplete) {

    }

    @Override
    public void getById(String id, Consumer<T> onComplete) {

    }

    private CollectionReference getCollection() {
        return mDb.collection(mCollectionName);
    }
}
