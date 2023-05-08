package com.ll.gramgram.boundedContext.notification.evenListener;

import com.ll.gramgram.base.event.EventAfterFromInstaMemberChangeGender;
import com.ll.gramgram.base.event.EventAfterLike;
import com.ll.gramgram.base.event.EventAfterModifyAttractiveType;
import com.ll.gramgram.base.event.EventBeforeCancelLike;
import com.ll.gramgram.boundedContext.instaMember.service.InstaMemberService;
import com.ll.gramgram.boundedContext.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final NotificationService notificationService;

    @EventListener
    @Transactional
    public void listen(EventAfterModifyAttractiveType event) {
        notificationService.whenAfterModifyAttractiveType(event.getLikeablePerson(), event.getOldAttractiveTypeCode());
    }

    @EventListener
    public void listen(EventAfterLike event) {
        notificationService.whenAfterLike(event.getLikeablePerson());
    }

    @EventListener
    public void listen(EventBeforeCancelLike event) {
        notificationService.whenAfterCancelLike(event.getLikeablePerson());
    }

    @EventListener
    public void listen(EventAfterFromInstaMemberChangeGender event) {
        notificationService.whenAfterFromInstaMemberChangeGender(event.getLikeablePerson(), event.getOldGender());
    }
}
