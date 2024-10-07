/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.quickstarts.todos;

import static java.util.Optional.ofNullable;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.ws.rs.core.UriBuilder;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private Boolean completed = false;
    @Column(name = "\"order\"")
    private int order;
    private URL url;

    public ToDo() {

    }

    public ToDo(String title) {
        this.title = title;
    }

    public ToDo(Long id, String title, Boolean completed, int order, URL url) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getCompleted(){
        return this.completed == null ? false : this.completed;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public URL getUrl() throws MalformedURLException, URISyntaxException {
        if (this.id != null) {
            return UriBuilder.fromUri(url.toURI()).scheme(url.getProtocol()).path(id.toString()).build().toURL();
        }
        return this.url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void update(ToDo newTodo) {
        this.title = ofNullable(newTodo.title).orElse(title);
        this.completed = ofNullable(newTodo.completed).orElse(completed);
        this.order = ofNullable(newTodo.order).orElse(order);
        this.url = ofNullable(newTodo.url).orElse(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                ", url=" + url +
                '}';
    }
}
