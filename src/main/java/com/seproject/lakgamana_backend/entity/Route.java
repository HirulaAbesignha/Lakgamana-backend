package com.seproject.lakgamana_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Route {
    @Id
    private Long id;

    public Route() {}
    public Route(Long id) { this.id = id; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}