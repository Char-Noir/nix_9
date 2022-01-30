package ua.com.alevel.web.controller;

public final class ControllerUtils {

    public static final AbstractController.HeaderName[] DOCTOR_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("Name", "name", "name"),
            new AbstractController.HeaderName("Date Of Certification", "date_of_certification", "date_of_certification"),
            new AbstractController.HeaderName("Category", "category", "category"),
            new AbstractController.HeaderName("Specialization", "specializations", "specializations"),
            new AbstractController.HeaderName("Details", null, null)
    };


    public static final AbstractController.HeaderName[] PATIENT_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("Name", "name", "name"),
            new AbstractController.HeaderName("Date Of Birth", "date_of_birth", "date_of_birth"),
            new AbstractController.HeaderName("Phone number", "phone_number", "phone_number"),
            new AbstractController.HeaderName("Details", null, null)
    };

    public static final AbstractController.HeaderName[] USER_COLUMNS = new AbstractController.HeaderName[]{
            new AbstractController.HeaderName("#", null, null),
            new AbstractController.HeaderName("Email", "email", "email"),
            new AbstractController.HeaderName("Role", "role_type", "role_type"),
            new AbstractController.HeaderName("Details", null, null)
    };
}
