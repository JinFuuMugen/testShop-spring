package com.testshop.testshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
    @Entity
    public class Product {
        @Id
        @GeneratedValue

        @Column
        private Long id;

        @Column
        private String name;

        @Column
        private String description;

        @Column
        private double price;

        @Column
        private int stock;

        @Column
        private boolean isActive;

        @Column
        private LocalDateTime createdAt;

        @Column
        private LocalDateTime updatedAt;


        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getStock() {
            return stock;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }
        //TODO:  constructors, getters, setters
    }
