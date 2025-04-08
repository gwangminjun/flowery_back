package com.example.flowery_backend.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 꽃 정보를 저장하는 엔터티 클래스입니다.
 */
@Builder
@Entity
@Table(name = "flower", schema = "public")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "f_low_nm")
    private String flowNm;

    @Column(name = "f_sct_nm")
    private String fSctNm;

    @Column(name = "f_eng_nm")
    private String fEngNm;

    @Column(name = "f_low_lang")
    private String flowLang;

    @Column(name = "f_content")
    private String fContent;

    @Column(name = "f_use")
    private String fUse;

    @Column(name = "f_grow")
    private String fGrow;

    @Column(name = "f_type")
    private String fType;

    @Column(name = "file_name1")
    private String fileName1;

    @Column(name = "file_name2")
    private String fileName2;

    @Column(name = "file_name3")
    private String fileName3;

    @Column(name = "img_url1")
    private String imgUrl1;

    @Column(name = "img_url2")
    private String imgUrl2;

    @Column(name = "img_url3")
    private String imgUrl3;

    @Column(name = "f_month")
    private String fMonth;

    @Column(name = "f_day")
    private String fDay;

    @Column(name = "f_means")
    private String fMeans;

    @Column(name = "f_season")
    private String fSeason;
}
