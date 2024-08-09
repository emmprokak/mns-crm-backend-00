package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.VoiceCall;

import java.util.Date;

public class VoiceCallBuilder implements EntityBuilder<VoiceCall>{
    private VoiceCall voiceCall;

    @Override
    public VoiceCall build() {
        return voiceCall;
    }

    public VoiceCallBuilder(){
        this.voiceCall = new VoiceCall();
    }

    public VoiceCallBuilder(VoiceCall voiceCall){
        this.voiceCall = voiceCall;
    }

    public VoiceCallBuilder setTitle(String title) {
        voiceCall.setTitle(title);
        return this;
    }

    public VoiceCallBuilder setCustomerPhone(String customerPhone) {
        voiceCall.setCustomerPhone(customerPhone);
        return this;
    }

    public VoiceCallBuilder setAgentName(String agentName) {
        voiceCall.setAgentName(agentName);
        return this;
    }

    public VoiceCallBuilder setCallDate(Date callDate) {
        voiceCall.setCallDate(callDate);
        return this;
    }

    public VoiceCallBuilder setDuration(int duration) {
        voiceCall.setDuration(duration);
        return this;
    }


}
