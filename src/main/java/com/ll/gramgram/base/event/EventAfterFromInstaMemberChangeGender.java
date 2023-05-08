package com.ll.gramgram.base.event;

import com.ll.gramgram.boundedContext.instaMember.entity.InstaMember;
import com.ll.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventAfterFromInstaMemberChangeGender extends ApplicationEvent {
    private final LikeablePerson likeablePerson;
    private final String oldGender;

    public EventAfterFromInstaMemberChangeGender(Object source, LikeablePerson likeablePerson, String oldGender) {
        super(source);
        this.likeablePerson = likeablePerson;
        this.oldGender = oldGender;
    }
}
