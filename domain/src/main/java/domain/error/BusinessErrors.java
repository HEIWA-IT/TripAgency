package {{project_namespace}}.{{project_name}}.domain.error;

public enum BusinessErrors {
    ;

    private final String code;

    BusinessErrors(final String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}