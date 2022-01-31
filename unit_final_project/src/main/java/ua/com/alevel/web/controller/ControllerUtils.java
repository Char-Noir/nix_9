package ua.com.alevel.web.controller;

public final class ControllerUtils {

    public static final AbstractController.HeaderName[] DOCTOR_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("Name", "name", "name"),
            new AbstractController.HeaderName("Date Of Certification", "dateOfCertification", "dateOfCertification"),
            new AbstractController.HeaderName("Category", "category", "category"),
            new AbstractController.HeaderName("Specialization", "specializations", "specialization"),
            new AbstractController.HeaderName("Details", null, null)
    };


    public static final AbstractController.HeaderName[] PATIENT_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("Name", "name", "name"),
            new AbstractController.HeaderName("Date Of Birth", "dateOfBirth", "dateOfBirth"),
            new AbstractController.HeaderName("Phone number", "phoneNumber", "phoneNumber"),
            new AbstractController.HeaderName("Details", null, null)
    };

    public static final AbstractController.HeaderName[] USER_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("Email", "email", "email"),
            new AbstractController.HeaderName("Role", "roleType", "roleType"),
            new AbstractController.HeaderName("Details", null, null)
    };

    public static final AbstractController.HeaderName[] RECEPTION_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("DateTime", "datetime", "datetime"),
            new AbstractController.HeaderName("Doctor", "doctor", "doctor"),
            new AbstractController.HeaderName("Patient", "patient", "patient"),
            new AbstractController.HeaderName("Details", null, null)
    };
}
