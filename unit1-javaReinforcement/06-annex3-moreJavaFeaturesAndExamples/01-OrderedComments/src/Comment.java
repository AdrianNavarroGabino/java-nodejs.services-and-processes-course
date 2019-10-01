// Adrián Navarro Gabino

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Comment {
    private String username;
    private String comment;
    private ZonedDateTime date;

    public Comment(String username, String comment, String day,
                   String hour, String zonetime) {
        this.username = username;
        this.comment = comment;

        String[] dateAux = day.split("/");
        String[] hourAux = hour.split(":");

        date = ZonedDateTime.of(Integer.parseInt(dateAux[2]),
                Integer.parseInt(dateAux[1]), Integer.parseInt(dateAux[0]),
                Integer.parseInt(hourAux[0]), Integer.parseInt(hourAux[1]),
                        Integer.parseInt(hourAux[2]), 0, ZoneId.of(zonetime));
    }

    public void setEuropeMadrid()
    {
        date.withZoneSameInstant(ZoneId.of("Europe/Madrid"));
    }

    @Override
    public String toString() {
        return "Comment{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }
}
