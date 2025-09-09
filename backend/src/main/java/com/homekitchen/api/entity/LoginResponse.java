package com.homekitchen.api.entity;

public record LoginResponse(
    int id, String firstName, String lastName, String email, String token) 
{

}
