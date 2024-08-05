package com.unipi.mns.mnscrm00.dal;

import com.unipi.mns.mnscrm00.entities.data.Task;
import com.unipi.mns.mnscrm00.entities.data.VoiceCall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoiceCallRepository extends JpaRepository<VoiceCall, String> {
}
