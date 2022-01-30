package ua.com.alevel.persistence.converters;

import ua.com.alevel.persistence.type.RoleType;

public class UserRoleConverter {

    public static RoleType convertStringToRoleType(String string) {
        return switch (string) {
            case "admin" -> RoleType.ROLE_ADMIN;
            case "user" -> RoleType.ROLE_PERSONAL;
            case "patient" -> RoleType.ROLE_PATIENT;
            case "doctor" -> RoleType.ROLE_DOCTOR;
            case "patient_doctor" -> RoleType.ROLE_PATIENT_DOCTOR;
            case "deleted" -> RoleType.ROLE_DELETED;
            default -> throw new RuntimeException("There are no such role: " + string);
        };
    }

    public static String convertRoleTypeToString(RoleType roleType) {
        return switch (roleType) {
            case ROLE_ADMIN -> "admin";
            case ROLE_PERSONAL -> "user";
            case ROLE_DOCTOR -> "doctor";
            case ROLE_PATIENT -> "patient";
            case ROLE_PATIENT_DOCTOR -> "patient_doctor";
            case ROLE_DELETED -> "deleted";
            default -> throw new RuntimeException("There are no such role");
        };
    }

}
