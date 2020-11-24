import java.util.Objects;

public class LinkPOJO {

    private String url;
    private boolean isAbsolute;
    private boolean isRelative;

    public LinkPOJO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    public void setAbsolute(boolean absolute) {
        isAbsolute = absolute;
    }

    public boolean isRelative() {
        return isRelative;
    }

    public void setRelative(boolean relative) {
        isRelative = relative;
    }

    @Override
    public String toString() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkPOJO linkPOJO = (LinkPOJO) o;
        return url.equals(linkPOJO.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
