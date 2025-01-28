package service;

import data.LibraryData;
import model.Admin;
import interfaces.AdminOperations;

public class AdminService implements AdminOperations {

    private static final String APPROVAL_CODE = "APPROVED";

    @Override
    public void addAdmin(Admin admin) {
        LibraryData.getUsers().put(admin.getId(), admin);
    }

    @Override
    public Admin findAdminById(String adminId) {
        return (Admin) LibraryData.getUsers().get(adminId);
    }

    public boolean isApprovalCodeValid(String code) {
        return APPROVAL_CODE.equals(code);
    }
}