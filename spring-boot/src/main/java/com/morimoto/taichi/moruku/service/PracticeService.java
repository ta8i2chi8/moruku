package com.morimoto.taichi.moruku.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morimoto.taichi.moruku.common.config.SecurityConfig;
import com.morimoto.taichi.moruku.domain.entity.Practice;
import com.morimoto.taichi.moruku.domain.entity.User;
import com.morimoto.taichi.moruku.domain.repository.ParticipantionMapper;
import com.morimoto.taichi.moruku.domain.repository.PracticeMapper;
import com.morimoto.taichi.moruku.domain.repository.UserMapper;
import com.morimoto.taichi.moruku.exception.NoSuchIdException;
import com.morimoto.taichi.moruku.exception.NotMyEntityException;

@Service
public class PracticeService {

    @Autowired
    private PracticeMapper practiceMapper;

    @Autowired
    private ParticipantionMapper participantionMapper;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserMapper userMapper;

    public List<Practice> findAll(Integer limit, Integer offset) {
        List<Practice> practices = practiceMapper.findAll(limit, offset);

        if (Objects.isNull(practices)) {
            return Collections.emptyList();
        }
        return practices;
    }

    public List<Practice> search(
        Integer limit, 
        Integer offset, 
        Integer prefectureId, 
        LocalDate from,
        LocalDate to,
        String keyword
    ) {
        List<Practice> practices = practiceMapper.search(
            limit, 
            offset, 
            prefectureId,
            from,
            to,
            keyword
        );

        if (Objects.isNull(practices)) {
            return Collections.emptyList();
        }
        return practices;
    }

    public Practice findById(UUID uuid) throws NoSuchIdException {
        Practice practice = practiceMapper.findById(uuid);
        
        if (Objects.isNull(practice)) {
            throw new NoSuchIdException("practices tableにuuid " + uuid + "が存在していません");
        }
        return practice;
    }

    public void insert(Practice practice) throws NoSuchIdException {
        String firebaseUid = securityConfig.getFirebaseUid();
        User user = userMapper.findByFirebaseUid(firebaseUid);

        // organizerIdの存在チェック
        if (Objects.isNull(user)) {
            throw new NoSuchIdException("users tableにuuid " + firebaseUid + "が存在していません");
        }

        practice.setOrganizerId(user.getUuid());
        practiceMapper.insert(practice);
    }

    public void update(Practice practice) throws NoSuchIdException, NotMyEntityException {
        String firebaseUid = securityConfig.getFirebaseUid();
        User user = userMapper.findByFirebaseUid(firebaseUid);

        // 自分で開催した練習会かどうかをチェック
        Practice currentPractice = practiceMapper.findById(practice.getUuid());
        UUID organizerId = currentPractice.getOrganizerId();
        if (!(organizerId.equals(user.getUuid()))) {
            throw new NotMyEntityException("ユーザーのUUID(" + user.getUuid().toString() + ")が、練習会の開催者のUUID(" + organizerId + ")と一致しません");
        }

        int updatedCount = practiceMapper.update(practice);

        if (updatedCount == 0) {
            throw new NoSuchIdException("practices tableにuuid " + practice.getUuid().toString() + "が存在していません");
        }
    }

    public void delete(UUID uuid) throws NoSuchIdException {
        int deletedCount = practiceMapper.delete(uuid);

        if (deletedCount == 0) {
            throw new NoSuchIdException("practices tableにuuid " + uuid + "が存在していません");
        }
    }

    public void joinPractice(UUID practiceId) throws NoSuchIdException {
        // practiceIdの存在チェック
        Practice practice = practiceMapper.findById(practiceId);        
        if (Objects.isNull(practice)) {
            throw new NoSuchIdException("practices tableにuuid " + practiceId + "が存在していません");
        }
        
        String firebaseUid = securityConfig.getFirebaseUid();

        // firebaseUidの存在チェック
        User user = userMapper.findByFirebaseUid(firebaseUid);        
        if (Objects.isNull(user)) {
            throw new NoSuchIdException("users tableにfirebase_uid " + firebaseUid + "が存在していません");
        }

        participantionMapper.insert(practiceId, firebaseUid);
    }

    public void cancelPractice(UUID practiceId) throws NoSuchIdException {
        // practiceIdの存在チェック
        Practice practice = practiceMapper.findById(practiceId);        
        if (Objects.isNull(practice)) {
            throw new NoSuchIdException("practices tableにuuid " + practiceId + "が存在していません");
        }
        
        String firebaseUid = securityConfig.getFirebaseUid();

        // firebaseUidの存在チェック
        User user = userMapper.findByFirebaseUid(firebaseUid);        
        if (Objects.isNull(user)) {
            throw new NoSuchIdException("users tableにfirebase_uid " + firebaseUid + "が存在していません");
        }

        int deletedCount = participantionMapper.delete(practiceId, firebaseUid);
        if (deletedCount == 0) {
            throw new NoSuchIdException("participations tableにpractice_id: " + practiceId.toString() + ", user_firebase_uid: " + firebaseUid + "のレコードが存在しません");
        }
    }
}