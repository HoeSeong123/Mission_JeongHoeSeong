package com.ll.gramgram.boundedContext.notification.service;

import com.ll.gramgram.base.appConfig.AppConfig;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMemberSnapshot;
import com.ll.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import com.ll.gramgram.boundedContext.notification.entity.Notification;
import com.ll.gramgram.boundedContext.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> findByToInstaMember(InstaMember toInstaMember) {
        return notificationRepository.findByToInstaMember(toInstaMember);
    }

    public void whenAfterModifyAttractiveType(LikeablePerson likeablePerson, int oldAttractiveTypeCode) {
        InstaMember fromInstaMember = likeablePerson.getFromInstaMember();
        InstaMember toInstaMember = likeablePerson.getToInstaMember();

        Notification notification = Notification
                .builder()
                .readDate(null)
                .fromInstaMember(fromInstaMember) // 호감을 표시하는 사람의 인스타 멤버
                .toInstaMember(toInstaMember) // 호감을 받는 사람의 인스타 멤버
                .typeCode("ModifyAttractiveType")
                .oldGender(null)
                .oldAttractiveTypeCode(oldAttractiveTypeCode)
                .newGender(fromInstaMember.getGender())
                .newAttractiveTypeCode(likeablePerson.getAttractiveTypeCode())
                .build();

        notificationRepository.save(notification);
    }

    public void whenAfterLike(LikeablePerson likeablePerson) {
        InstaMember fromInstaMember = likeablePerson.getFromInstaMember();
        InstaMember toInstaMember = likeablePerson.getToInstaMember();

        Notification notification = Notification
                .builder()
                .readDate(null)
                .fromInstaMember(fromInstaMember) // 호감을 표시하는 사람의 인스타 멤버
                .toInstaMember(toInstaMember) // 호감을 받는 사람의 인스타 멤버
                .typeCode("Like")
                .oldGender(null)
                .oldAttractiveTypeCode(0)
                .newGender(fromInstaMember.getGender())
                .newAttractiveTypeCode(likeablePerson.getAttractiveTypeCode())
                .build();

        notificationRepository.save(notification);
    }

    public void updateReadDate(List<Notification> notifications) {
        for(Notification notification : notifications) {
            if(notification.getReadDate() == null) {
                notification.setReadDate(LocalDateTime.now());
            }
        }
    }

//    public void whenBeforeCancelLike(LikeablePerson likeablePerson) {
//        InstaMember fromInstaMember = likeablePerson.getFromInstaMember();
//        InstaMember toInstaMember = likeablePerson.getToInstaMember();
//
//        toInstaMember.decreaseLikesCount(fromInstaMember.getGender(), likeablePerson.getAttractiveTypeCode());
//    }

//    public void whenAfterFromInstaMemberChangeGender(LikeablePerson likeablePerson, String oldGender) {
//        InstaMember fromInstaMember = likeablePerson.getFromInstaMember();
//        InstaMember toInstaMember = likeablePerson.getToInstaMember();
//
//        Notification notification = Notification
//                .builder()
//                .readDate(null)
//                .fromInstaMember(fromInstaMember) // 호감을 표시하는 사람의 인스타 멤버
//                .toInstaMember(toInstaMember) // 호감을 받는 사람의 인스타 멤버
//                .typeCode("Like")
//                .oldGender(null)
//                .oldAttractiveTypeCode(0)
//                .newGender(fromInstaMember.getGender())
//                .newAttractiveTypeCode(likeablePerson.getAttractiveTypeCode())
//                .build();
//
//        notificationRepository.save(notification);
//    }
}
