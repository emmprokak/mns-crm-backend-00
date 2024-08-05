package com.unipi.mns.mnscrm00.controllers;

import com.unipi.mns.mnscrm00.dto.abstracts.VoiceCallDTO;
import com.unipi.mns.mnscrm00.entities.data.VoiceCall;
import com.unipi.mns.mnscrm00.services.concretes.VoiceCallService;
import com.unipi.mns.mnscrm00.services.concretes.VoiceCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/call")
public class VoiceCallController {

    @Autowired
    private VoiceCallService voiceCallService;

    @GetMapping("/{id}")
    public VoiceCallDTO getVoiceCall(@PathVariable String id){
        return voiceCallService.getVoiceCallByIdSimple(id);
    }

    @GetMapping("/{id}/complete")
    public VoiceCallDTO getVoiceCallWithRels(@PathVariable String id){
        return voiceCallService.getVoiceCallByIdComplete(id);
    }

    @PostMapping("/new")
    public VoiceCallDTO createVoiceCall(@RequestBody VoiceCall voiceCall){
        return voiceCallService.insertVoiceCall(voiceCall);
    }

    @GetMapping("/all")
    public List<VoiceCallDTO> getVoiceCalls(){
        return voiceCallService.getAllVoiceCalls();
    }

    @PutMapping("/{id}")
    public VoiceCallDTO updateVoiceCall(@PathVariable String id, @RequestBody VoiceCall voiceCall){
        return voiceCallService.updateVoiceCall(id, voiceCall);
    }

    @DeleteMapping("/{id}")
    public boolean deleteVoiceCall(@PathVariable String id){
        return voiceCallService.deleteVoiceCallById(id);
    }
}
