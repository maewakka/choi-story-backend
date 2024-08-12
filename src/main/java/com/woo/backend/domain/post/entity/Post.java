package com.woo.backend.domain.post.entity;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "POST_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="POST_SEQUENCE_GENERATOR", sequenceName = "POST_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    private User author;

    private String title;
    private String thumbnailImgUrl;

}
