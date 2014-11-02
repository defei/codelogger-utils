package org.codelogger.utils.beans;

import static org.codelogger.utils.ArrayUtils.subArray;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

import org.codelogger.utils.ArrayUtils;

/**
 * StorageComponent is a raw type, support variable keys. References to generic
 * type StorageComponent<K,V> should be parameterized.
 * 
 * @author DengDefei
 * 
 * @param <KeyType>
 *            class type of keys.
 * @param <ValueType>
 *            class type of values.
 */
public class StorageComponent<KeyType, ValueType> {

    private KeyToStorageComponent<KeyType, ValueType> keyToStorage;

    private LinkedHashMap<KeyType, ValueType> keyToValue;

    public StorageComponent() {

    }

    /**
     * Instantiation a StorageComponent instance, and story given value by given
     * keys.
     * 
     * @param value
     *            the value to storage.
     * @param keys
     *            the keys to storage value.
     */
    public StorageComponent(final ValueType value, final KeyType... keys) {

        if (ArrayUtils.isEmpty(keys)) {
            return;
        }
        if (keys.length == 1) {
            put(value, keys[0]);
        } else {
            StorageComponent<KeyType, ValueType> childComponent = new StorageComponent<KeyType, ValueType>(
                    value, subArray(keys, 1));
            getkeyToStorage().put(keys[0], childComponent);
        }
    }

    /**
     * Returns value of keys reference.
     * 
     * @param keys
     *            the keys to get value.
     * @return value of keys reference.
     */
    public ValueType get(final KeyType... keys) {

        if (ArrayUtils.isEmpty(keys)) {
            return null;
        }
        int keysLength = keys.length;
        if (keysLength == 1) {
            return get(keys[0]);
        } else {
            StorageComponent<KeyType, ValueType> storageComponent = this;
            int lastKeyIndex = keysLength - 1;
            for (int i = 0; i < lastKeyIndex; i++) {
                KeyType storageComponentKey = keys[i];
                storageComponent = storageComponent.getStorageComponent(storageComponentKey);
            }
            return storageComponent.get(keys[lastKeyIndex]);
        }
    }

    /**
     * Returns value of key reference.
     * 
     * @param key
     *            the key to get value.
     * @return value of key reference.
     */
    public ValueType get(final KeyType key) {

        return getKeyToValue().get(key);
    }

    /**
     * Returns a Set view of the keys contained in this StorageComponent.
     * 
     * @return a Set view of the keys contained in this StorageComponent.
     */
    public Set<KeyType> keySet() {

        return getKeyToValue().keySet();
    }

    /**
     * Returns a Collection view of the values contained in this
     * StorageComponent.
     * 
     * @return a Collection view of the values contained in this
     *         StorageComponent.
     */
    public Collection<ValueType> values() {

        return getKeyToValue().values();
    }

    /**
     * Returns the number of key-value mappings in this StorageComponent.
     * 
     * @return the number of key-value mappings in this StorageComponent.
     */
    public int size() {

        return getKeyToValue().size();
    }

    /**
     * Returns the number of key-value mappings in this StorageComponent
     * Component.
     * 
     * @return the number of key-value mappings in this StorageComponent
     *         Component.
     */
    public int componentSize() {

        return getkeyToStorage().size();
    }

    /**
     * Storages the value by keys.
     * 
     * @param value
     *            the value to storage.
     * @param keys
     *            the keys to storage value.
     */
    public void put(final ValueType value, final KeyType... keys) {

        if (ArrayUtils.isEmpty(keys)) {
            return;
        }
        int keysLength = keys.length;
        if (keysLength == 1) {
            put(value, keys[0]);
        } else {
            StorageComponent<KeyType, ValueType> childStorageComponent = getStorageComponent(keys[0]);
            int lastKeyIndex = keysLength - 1;
            for (int i = 1; i < lastKeyIndex; i++) {
                childStorageComponent = childStorageComponent.getStorageComponent(keys[i]);
            }
            childStorageComponent.put(value, keys[lastKeyIndex]);
        }
    }

    /**
     * Storages the value by key.
     * 
     * @param value
     *            the value to storage.
     * @param key
     *            the key to storage value.
     */
    public void put(final ValueType value, final KeyType key) {

        getKeyToValue().put(key, value);
    }

    /**
     * Returns child StorageComponent by given key.
     * 
     * @param key
     *            the key of children StorageComponent.
     * @return a StorageComponent object.
     */
    public StorageComponent<KeyType, ValueType> getStorageComponent(final KeyType key) {

        KeyToStorageComponent<KeyType, ValueType> storage = getkeyToStorage();
        StorageComponent<KeyType, ValueType> storageComponent = storage.get(key);
        if (storageComponent == null) {
            storageComponent = new StorageComponent<KeyType, ValueType>();
            storage.put(key, storageComponent);
        }
        return storageComponent;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("{");
        if (keyToStorage != null) {
            stringBuilder.append("keyToStorage : ");
            stringBuilder.append(keyToStorage.toString());
        }
        if (keyToValue != null) {
            if (keyToStorage != null) {
                stringBuilder.append(",");
            }
            stringBuilder.append("keyToValue : ");
            stringBuilder.append(keyToValue.toString());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private KeyToStorageComponent<KeyType, ValueType> getkeyToStorage() {

        if (keyToStorage == null) {
            keyToStorage = new KeyToStorageComponent<KeyType, ValueType>();
        }
        return keyToStorage;
    }

    private LinkedHashMap<KeyType, ValueType> getKeyToValue() {

        if (keyToValue == null) {
            keyToValue = new LinkedHashMap<KeyType, ValueType>();
        }
        return keyToValue;
    }

    @SuppressWarnings("hiding")
    private class KeyToStorageComponent<KeyType, ValueType> extends
            LinkedHashMap<KeyType, StorageComponent<KeyType, ValueType>> {

        private static final long serialVersionUID = 2286518117207413670L;

    }
}
