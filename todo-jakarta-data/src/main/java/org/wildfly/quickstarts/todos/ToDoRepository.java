/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.quickstarts.todos;

import java.util.List;
import java.util.Optional;

import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Update;

@Repository
public interface ToDoRepository {

    @Find
    List<ToDo> findAll();

    @Find
    Optional<ToDo> find(Long id);

    @Delete
    void remove(ToDo todo);

    @Insert
    void insert(ToDo todo);

    @Update
    ToDo update(ToDo todo);

    /**
     * Update the {@code ToDo} with the given id, if one exists.
     *
     * @param id the id of the ToDo to update. Cannot be {@code null}
     * @param update the updated ToDo to persist if one with the id exists. Cannot be {@code null
     * @return an {@link Optional} containing the updated ToDo if a ToDo with {@code id} existed
     */
    default Optional<ToDo> update(Long id, ToDo update) {
        Optional<ToDo> optional = find(id);
        return optional.isPresent()? Optional.of(update(update)) : optional;
    }
}