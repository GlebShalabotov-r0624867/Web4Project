package domain;

public class BlogAntwoorden {
    int blogid;
    String user;
    String antwoord;
    int rating;

    public BlogAntwoorden(int id, String user, String antwoord, int rating) {
        setUser(user);
        setAntwoord(antwoord);
        setRating(rating);
        setBlogid(id);
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        } else {
            throw new NullPointerException("rating is out of range");
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBlogid() {
        return blogid;
    }

    public void setBlogid(int blogid) {
        this.blogid = blogid;
    }
}
