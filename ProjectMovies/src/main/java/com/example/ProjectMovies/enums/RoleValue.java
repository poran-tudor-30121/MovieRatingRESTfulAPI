package com.example.ProjectMovies.enums;

public enum RoleValue {

    ADMIN("admin"),
    USER("user");
    private String roleDescription;

    RoleValue (String roleDescription) {
        this.roleDescription = roleDescription;
    }
    public String getRoleDescription()
    {
        return roleDescription;
    }
    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription=roleDescription;
    }

}
