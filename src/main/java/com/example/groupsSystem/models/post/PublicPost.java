package com.example.groupsSystem.models.post;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PublicPost extends Post {


    public PublicPost() {
    }

    public PublicPost(String tittle, String body, NormalUser writer, List<Comment> commentList, boolean accepted) {
        super(tittle, body, writer, commentList , accepted);
    }

}
