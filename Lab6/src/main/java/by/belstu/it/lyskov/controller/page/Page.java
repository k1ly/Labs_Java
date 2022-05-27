package by.belstu.it.lyskov.controller.page;

public enum Page {
    WELCOME_PAGE("/jsp/welcome.jsp"),
    WARNING_PAGE("/jsp/warning.jsp"),
    REGISTER_PAGE("/jsp/register.jsp"),
    SIGN_IN_PAGE("/jsp/signIn.jsp"),
    ITEM_LIST("/jsp/itemList.jsp"),
    ADD_ITEM("/jsp/addItem.jsp");

    private static final String contextPath = "/lab6";
    private final String page;

    Page(String path) {
        this.page = path;
    }

    public static String getContextPath() {
        return contextPath;
    }

    public String getPath() {
        return contextPath + page;
    }

    public String getPage() {
        return page;
    }
}
