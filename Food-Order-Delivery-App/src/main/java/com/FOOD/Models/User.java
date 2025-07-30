    package com.FOOD.Models;
    
    import com.FOOD.Dto_data_transfer_object.RestaurantDto;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    import java.util.List;
    
    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    public class User {
    
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;
        private String fullName;
        private String email;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;
    
        @JsonIgnore
        @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
        private List<Order> orders;
    
        @ElementCollection
        private List<RestaurantDto> favorites;
    
        @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
        private List<Addresses> addressess;
    
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getFullName() {
            return fullName;
        }
    
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    
        public USER_ROLE getRole() {
            return role;
        }
    
        public void setRole(USER_ROLE role) {
            this.role = role;
        }
    
        public List<Order> getOrders() {
            return orders;
        }
    
        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }
    
        public List<RestaurantDto> getFavorites() {
            return favorites;
        }
    
        public void setFavorites(List<RestaurantDto> favorites) {
            this.favorites = favorites;
        }
    
        public List<Addresses> getAddressess() {
            return addressess;
        }
    
        public void setAddressess(List<Addresses> addressess) {
            this.addressess = addressess;
        }
    }
