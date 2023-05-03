package com.ll.gramgram.boundedContext.notification.entity;

import com.ll.gramgram.base.appConfig.AppConfig;
import com.ll.gramgram.base.baseEntity.BaseEntity;
import com.ll.gramgram.base.rsData.RsData;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Notification extends BaseEntity {
    private LocalDateTime readDate;
    @ManyToOne
    @ToString.Exclude
    private InstaMember toInstaMember; // 메세지 받는 사람(호감 받는 사람)
    @ManyToOne
    @ToString.Exclude
    private InstaMember fromInstaMember; // 메세지를 발생시킨 행위를 한 사람(호감표시한 사람)
    private String typeCode; // 호감표시=Like, 호감사유변경=ModifyAttractiveType,
    private String oldGender; // 해당사항 없으면 null
    private int oldAttractiveTypeCode; // 해당사항 없으면 0
    private String newGender; // 해당사항 없으면 null
    private int newAttractiveTypeCode; // 해당사항 없으면 0

    public String getElapsedTimeStrHuman() {
        Duration duration = Duration.between(getCreateDate(), LocalDateTime.now());
        long hour = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if(hour <= 0 && minutes <= 0) return "방금 전";
        else if(hour <= 0 && minutes > 0) return "%s분 전".formatted(minutes);
        else return "%s시간 전".formatted(hour);
    }

    public String getOldAttractiveTypeDisplayName() {
        return switch (oldAttractiveTypeCode) {
            case 1 -> "외모";
            case 2 -> "성격";
            default -> "능력";
        };
    }

    public void updateReadDate() {
        if (this.readDate != null) {
            return;
        }

        this.readDate = LocalDateTime.now();
    }

    public String getNewAttractiveTypeDisplayName() {
        return switch (newAttractiveTypeCode) {
            case 1 -> "외모";
            case 2 -> "성격";
            default -> "능력";
        };
    }

    public String getOldGenderDisplayName() {
        return switch (oldGender) {
            case "W" -> "여성";
            default -> "남성";
        };
    }

    public String getNewGenderDisplayName() {
        return switch (newGender) {
            case "W" -> "여성";
            default -> "남성";
        };
    }
}
