package com.example.flowery_backend.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 꽃 해시태그 정보를 저장하는 엔터티 클래스입니다.
 */
@Builder
@Entity
@Table(name = "flower_hashtag", schema = "public")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FlowerHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "flower_id", nullable = false)
    private Long flowerId;

    @Column(name = "tag_name", nullable = false)
    private String tagName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "flower_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Flower flower;
}