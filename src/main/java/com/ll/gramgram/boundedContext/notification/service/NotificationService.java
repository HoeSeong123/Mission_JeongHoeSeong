package com.ll.gramgram.boundedContext.notification.service;

import com.ll.gramgram.base.appConfig.AppConfig;
import com.ll.gramgram.base.event.EventAfterModifyAttractiveType;
import com.ll.gramgram.base.rsData.RsData;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import com.ll.gramgram.boundedContext.instaMember.entity.InstaMemberSnapshot;
import com.ll.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import com.ll.gramgram.boundedContext.notification.entity.Notification;
import com.ll.gramgram.boundedContext.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> findByToInstaMember(InstaMember toInstaMember) {
        return notificationRepository.findByToInstaMember(toInstaMember);
    }

    @Transactional
    public RsData<Notification> whenAfterModifyAttractiveType(LikeablePerson likeablePerson, int oldAttractiveTypeCode) {
        return make(likeablePerson, "ModifyAttractiveType", oldAttractiveTypeCode, likeablePerson.getFromInstaMember().getGender());
    }

    @Transactional
    public RsData<Notification> whenAfterLike(LikeablePerson likeablePerson) {
        return make(likeablePerson, "Like", 0, null);
    }

    @Transactional
    public RsData<Notification> modifyReadDate(List<Notification> notifications) {
        for(Notification notification : notifications) {
            notification.updateReadDate();
        }

        return RsData.of("S-1", "읽음 처리 되었습니다.");
    }

    @Transactional
    public RsData<Notification> whenAfterCancelLike(LikeablePerson likeablePerson) {
        return make(likeablePerson, "Cancel", 0, null);
    }

    @Transactional
    public RsData<Notification> whenAfterFromInstaMemberChangeGender(LikeablePerson likeablePerson, String oldGender) {
        return make(likeablePerson, "ModifyGender", 0, oldGender);
    }

    public RsData<Notification> make(LikeablePerson likeablePerson, String typeCode, int oldAttractiveTypeCode, String oldGender) {
        Notification notification = Notification
                .builder()
                .typeCode(typeCode)
                .toInstaMember(likeablePerson.getToInstaMember())
                .fromInstaMember(likeablePerson.getFromInstaMember())
                .oldAttractiveTypeCode(oldAttractiveTypeCode)
                .oldGender(oldGender)
                .newAttractiveTypeCode(likeablePerson.getAttractiveTypeCode())
                .newGender(likeablePerson.getFromInstaMember().getGender())
                .build();

        notificationRepository.save(notification);

        return RsData.of("S-1", "알림 메세지가 생성되었습니다.", notification);
    }

    public boolean countUnreadNotificationsByToInstaMember(InstaMember instaMember) {
        return notificationRepository.countByToInstaMemberAndReadDateIsNull(instaMember) > 0;
    }
}
