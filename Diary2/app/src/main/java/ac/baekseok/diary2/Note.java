package ac.baekseok.diary2;

import java.util.Collection;

public class Note{
   private int noteId;
   private String title;
   private String content;
   private String picture;
   private String date;

    public Note(int noteId, String title, String content, String picture, String date) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.date = date;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
