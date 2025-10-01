package com.seproject.lakgamana_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Train {
    @Id
    private Long id;

    public Train() {}
    public Train(Long id) { this.id = id; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
